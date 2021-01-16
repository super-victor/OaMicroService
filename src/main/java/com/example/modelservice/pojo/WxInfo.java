package com.example.modelservice.pojo;

import lombok.Data;

/**
 * @ClassName WxInfo
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/16 8:46
 * @Version v1.0
 */

@Data
public class WxInfo {
    private String openid;
    private String url;
    private String nickname;
    private String secretkey;
    private Integer employeeId;
}
