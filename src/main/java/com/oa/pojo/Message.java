package com.oa.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:34
 * @Description:消息实体类
 */
@Data
public class Message {
    private Integer messageId;
    private Integer employeeId;
    private Integer type;
    private String title;
    private String content;
    private Integer isRead;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;
}
