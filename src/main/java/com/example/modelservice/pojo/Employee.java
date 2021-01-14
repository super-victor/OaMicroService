package com.example.modelservice.pojo;

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
@Valid
public class Employee {
    private Integer employeeId;
    @Size(max = 20,min = 5,groups = Add.class)
    @NotNull(groups = Add.class)
    private String username;
    @NotNull(groups = Add.class)
    private String name;
    private Date birthday;
    @JsonIgnore
    @NotNull(groups = Add.class)
    @Size(max = 20,min = 5)
    private String password;
    @Pattern(regexp = "^[0-9]*$")
    @NotNull(groups = Add.class)
    private String phone;
    @Email
    @NotNull(groups = Add.class)
    private String email;
    @Pattern(regexp = "^[0-9]*$")
    @NotNull(groups = Add.class)
    private String idCard;
    @Pattern(regexp = "m|f")
    @NotNull(groups = Add.class)
    private String sex;
    private Date entryTime;
    @Size(max = 10,min = 1)
    @NotNull(groups = Add.class)
    private String departmentName;
    @Size(max = 10,min = 1)
    @NotNull(groups = Add.class)
    private String position;
    @Size(max = 20,min = 1)
    @NotNull(groups = Add.class)
    private String homeAddress;
    private int isAccountLocked;
    @JsonIgnore
    private Date passwordChangeDate;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    private List<Role> authorities;
    private Department department;

    public interface Add{
    }

    public interface Update{
    }



}
