package com.oa.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

/**
 * @ClassName Department
 * @Description 部门实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 15:47
 * @Version v1.0
 */

@Data
//@AllArgsConstructor
public class Department {
    private Integer departmentId;
    private String name;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}