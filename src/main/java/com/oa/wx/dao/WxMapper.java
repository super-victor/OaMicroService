package com.oa.wx.dao;

import com.oa.wx.pojo.WxInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @InterfaceName WxMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/16 9:12
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface WxMapper {

    @GetMapping("/selectFromWxinfo")
    WxInfo selectFromWxinfo(@RequestParam String openId);

    @PutMapping("/updateWxInfo")
    int updateWxInfo(@RequestBody WxInfo wxInfo);

    @PostMapping("/insertIntoWxinfo")
    int insertIntoWxinfo(@RequestBody WxInfo wxInfo);

    @DeleteMapping("/deleteWxinfo")
    int deleteWxinfo(String openid);
}
