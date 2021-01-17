package com.oa.wx.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * @ClassName Employee
 * @Description 职工实体类
 * @Author JohnTang
 * @LastChangeDate 2020/11/4 22:54
 * @Version v1.0
 */

@Data
public class Employee {
    private Integer employeeId;
    private String username;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
    private String password;
    private String phone;
    private String email;
    private String idCard;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date entryTime;
    private String departmentName;
    private String position;
    private String homeAddress;
    private int isAccountLocked;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date passwordChangeDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
