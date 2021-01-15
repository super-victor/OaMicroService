package com.example.modelservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
    private Date birthday;

    private String password;

    private String phone;

    private String email;

    private String idCard;
    private String sex;
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
    private List<Role> authorities;
    private Department department;



}
