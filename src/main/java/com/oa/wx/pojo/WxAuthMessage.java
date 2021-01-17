package com.oa.wx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName WxAuthMessage
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/15 21:13
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class WxAuthMessage {
    private String session_key;
    private String openid;
}
