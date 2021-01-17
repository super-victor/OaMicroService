package com.oa.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Log
 * @Description
 * @Author pan
 * @LastChangeDate 2020/12/3 23:51
 * @Version v1.0
 */
@Data
public class Log {

    @JsonIgnore
    private Integer logId;
    private String level;
    private String type;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date updateTime;
}

