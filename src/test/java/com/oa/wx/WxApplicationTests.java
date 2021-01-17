package com.oa.wx;

import com.oa.wx.request.AuthRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WxApplicationTests {
    @Resource
    AuthRequest authRequest;

    @Test
    void contextLoads() {
        System.out.println(authRequest.authInWx("093gOd0w3jVrFV20D91w3fonqs0gOd0x").toString());
    }

}
