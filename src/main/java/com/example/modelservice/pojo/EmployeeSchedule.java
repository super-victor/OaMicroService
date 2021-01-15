package com.example.modelservice.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName EmployeeSchedule
 * @Description 职工日程对应实体类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/5 20:16
 * @Version v1.0
 */

@Data
@AllArgsConstructor
public class EmployeeSchedule {
    private Integer employeeScheduleId;
    private Integer scheduleId;
    private Integer employeeId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonIgnore
    private Date updateTime;

    public EmployeeSchedule(){}
}
