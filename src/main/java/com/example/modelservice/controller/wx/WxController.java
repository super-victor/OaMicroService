package com.example.modelservice.controller.wx;

import com.example.modelservice.mapper.WxMapper;
import com.example.modelservice.pojo.WxInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName WxController
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/16 9:08
 * @Version v1.0
 */

@RestController
public class WxController {

    @Resource
    WxMapper wxMapper;

    @GetMapping("/selectFromWxinfo")
    public WxInfo selectFromWxinfo(@RequestParam String openId){
        return wxMapper.selectFromWxinfo(openId);
    }

    @PutMapping("/updateWxInfo")
    public int updateWxInfo(@RequestBody WxInfo wxInfo){
        return wxMapper.updateWxInfo(wxInfo);
    }

    @PostMapping("/insertIntoWxinfo")
    public int insertIntoWxinfo(@RequestBody WxInfo wxInfo){
        return wxMapper.insertIntoWxinfo(wxInfo);
    }

    @DeleteMapping("/deleteWxinfo")
    public int deleteWxinfo(String openid){
        return wxMapper.deleteWxinfo(openid);
    }
}
