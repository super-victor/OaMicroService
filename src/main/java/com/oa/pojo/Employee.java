package com.oa.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@ApiModel(value = "employee",description = "职工信息")
@Data
@Valid
public class Employee implements UserDetails {
    private Integer employeeId;
    @Size(max = 20,min = 5,groups = Add.class)
    @NotNull(groups = Add.class)
    private String username;
    @ApiModelProperty(required = true)
    @NotNull(groups = Add.class)
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
    @ApiModelProperty(required = true)
    @Size(max = 10,min = 1,message = "部门格式不正确")
    @NotNull(groups = Add.class)
    private String departmentName;
    @ApiModelProperty(required = true)
    @Size(max = 10,min = 1,message = "职位格式不正确")
    @NotNull(groups = Add.class)
    private String position;
    @ApiModelProperty(required = true)
    @Size(max = 20,min = 1,message = "地址格式不正确")
    @NotNull(groups = Add.class)
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

    public interface Add{
    }

    public interface Update{
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (isAccountLocked == 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
