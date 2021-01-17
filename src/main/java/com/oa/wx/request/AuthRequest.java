package com.oa.wx.request;

import com.google.gson.Gson;
import com.oa.wx.pojo.WxAuthMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName AutnRequest
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/15 16:41
 * @Version v1.0
 */

@Component
public class AuthRequest {
    @Value("${appid}")
    private String appId;

    @Value("${secret}")
    private String appSecret;

    @Value("${url}")
    private String url;

    private <T> T sendGetRequest(String url, Class<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url,responseType);
    }

    public WxAuthMessage authInWx(String code) {
        return new Gson().fromJson(sendGetRequest(url+"?appid="+appId+"&secret="+appSecret+"&js_code="+code+"&grant_type=authorization_code", String.class), WxAuthMessage.class);
    }
}
