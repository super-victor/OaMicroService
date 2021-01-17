package com.oa.wx.controller;

import com.oa.wx.dao.EmployeeMapper;
import com.oa.wx.dao.WxMapper;
import com.oa.wx.pojo.Employee;
import com.oa.wx.pojo.WxAuthMessage;
import com.oa.wx.pojo.WxInfo;
import com.oa.wx.request.AuthRequest;
import com.oa.wx.json.BackFrontMessage;
import com.oa.wx.util.JwtTokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AuthController
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/15 23:44
 * @Version v1.0
 */

@RestController
public class AuthController {

    @Resource
    AuthRequest authRequest;

    @Resource
    WxMapper wxMapper;

    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Resource
    EmployeeMapper employeeMapper;

    @GetMapping("/auth")
    public BackFrontMessage auth(@RequestParam String code){
        // 请求微信认证
        WxAuthMessage wxAuthMessage = authRequest.authInWx(code);
        // 微信认证不通过
        if (wxAuthMessage.getOpenid() == null) {
            return new BackFrontMessage(500, "code无效", null);
        }
        WxInfo wxInfo = wxMapper.selectFromWxinfo(wxAuthMessage.getOpenid());
        // 如果数据库中没找到对应用户信息，新建一个存储
        Map<String, Object> returnData = new HashMap<>(2);
        if (wxInfo == null || wxInfo.getOpenid() == null) {
            wxInfo = new WxInfo();
            wxInfo.setOpenid(wxAuthMessage.getOpenid());
            wxInfo.setSecretkey(wxAuthMessage.getSession_key());
            if (wxMapper.insertIntoWxinfo(wxInfo) != 0) {
                returnData.put("openId", wxInfo.getOpenid());
                return new BackFrontMessage(200, "用户未绑定",returnData);
            } else {
                returnData.put("openId", wxInfo.getOpenid());
                return new BackFrontMessage(500, "操作失败",returnData);
            }
        } else {
            // 数据库中拿到用户信息
            // 如果绑定了用户的
            if (wxInfo.getEmployeeId() != null) {
                Employee employee = employeeMapper.findEmployeeByEmployeeId(wxInfo.getEmployeeId());
                returnData.put("openId", wxInfo.getOpenid());
                returnData.put("token", jwtTokenUtil.generateToken(employee.getUsername()));
                employee.setPassword("");
                returnData.put("userInfo", employee);
                return new BackFrontMessage(200,"登录成功", employee);
            } else {
                returnData.put("openId", wxInfo.getOpenid());
                return new BackFrontMessage(200,"用户未绑定",employeeMapper.findEmployeeByEmployeeId(wxInfo.getEmployeeId()));
            }
        }
    }

    @PostMapping("/updateWxInfo")
    public BackFrontMessage updateWxInfo(WxInfo wxInfo){

        if (wxInfo.getOpenid() != null && wxMapper.updateWxInfo(wxInfo) != 0) {
            return new BackFrontMessage(200, "操作成功", null);
        } else {
            return new BackFrontMessage(500, "操作失败", null);
        }

    }

    @PostMapping("/binduser")
    public BackFrontMessage bindUser(@RequestParam String openid, @RequestParam String username, @RequestParam String password){
        WxInfo wxInfo = wxMapper.selectFromWxinfo(openid);
        if (wxInfo.getEmployeeId() != null) {
            return new BackFrontMessage(500, "已绑定", wxInfo);
        }
        Employee employee = employeeMapper.findEmployeeByUsername(username);
        // 密码正确
        if (employee.getPassword().equals(password)) {
            // 开始绑定
            wxInfo.setEmployeeId(employee.getEmployeeId());
            if (wxMapper.updateWxInfo(wxInfo) != 0){
                return new BackFrontMessage(200, "绑定成功", null);
            } else {
                return new BackFrontMessage(500, "绑定失败", null);
            }
        } else {
            return new BackFrontMessage(500,"用户名或密码错误", null);
        }
    }

    @GetMapping("/isbind")
    public BackFrontMessage isBind(@RequestParam String openid) {
        WxInfo wxInfo = wxMapper.selectFromWxinfo(openid);
        if (wxInfo.getEmployeeId() != null) {
            return new BackFrontMessage(200, "已绑定", wxInfo);
        } else {
            return new BackFrontMessage(500, "未绑定",null);
        }
    }



}
