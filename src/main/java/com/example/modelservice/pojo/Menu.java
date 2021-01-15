package com.example.modelservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Menu
 * @Description 菜单
 * @Author JohnTang
 * @LastChangeDate 2020/11/22 10:55
 * @Version v1.0
 */

@Data
public class Menu {
     private int menuId;
     private String name;
     @JsonIgnore
     private String url;
     @JsonIgnore
     private String code;
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     @JsonIgnore
     private Date createTime;
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
     @JsonIgnore
     private Date updateTime;
}
