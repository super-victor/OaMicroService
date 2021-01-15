package com.example.modelservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: pan
 * @Date: 2020/11/05/20:15
 * @Description: 职工角色对应实体类
 */
@Data
public class EmployeeRole {
    private Integer employeeRoleId;  //职工角色ID
    private Integer employeeId;     //职工ID
    private Integer RoleId;         //角色ID
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;       //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;       //修改时间
}
