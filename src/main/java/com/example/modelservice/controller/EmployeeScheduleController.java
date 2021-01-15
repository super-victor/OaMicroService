package com.example.modelservice.controller;

import com.example.modelservice.mapper.EmployeeScheduleMapper;
import com.example.modelservice.pojo.EmployeeSchedule;
import com.example.modelservice.pojo.Schedule;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName EmployeeScheduleController
 * @Description 职工日程控制层
 * @Author Waynejwei
 * @LastChangeDate 2021/1/14 19:07
 * @Version v1.0
 */

@RestController
public class EmployeeScheduleController {

    @Resource
    EmployeeScheduleMapper employeeScheduleMapper;

    @PostMapping("/insertEmployeeSchedule")
    int insertEmployeeSchedule(@RequestBody EmployeeSchedule employeeSchedule){
        return employeeScheduleMapper.insertEmployeeSchedule(employeeSchedule);
    }

    @DeleteMapping("/deleteEmployeeScheduleByScheduleId")
    int deleteEmployeeScheduleByScheduleId(@RequestParam int scheduleId){
        return employeeScheduleMapper.deleteEmployeeScheduleByScheduleId(scheduleId);
    }

    @GetMapping("/findEmployeeScheduleByScheduleId")
    List<Integer> findEmployeeScheduleByScheduleId(@RequestParam int scheduleId){
        return employeeScheduleMapper.findEmployeeScheduleByScheduleId(scheduleId);
    }

    @GetMapping("/findEmployeeScheduleByEmployeeId")
    List<Integer> findEmployeeScheduleByEmployeeId(@RequestParam int employeeId){
        return employeeScheduleMapper.findEmployeeScheduleByEmployeeId(employeeId);
    }

    @GetMapping("/findEmployeeScheduleByEmployeeIdAndScheduleId")
    EmployeeSchedule findEmployeeScheduleByEmployeeIdAndScheduleId(@RequestParam int employeeId, @RequestParam int scheduleId){
        return employeeScheduleMapper.findEmployeeScheduleByEmployeeIdAndScheduleId(employeeId, scheduleId);
    }

    @GetMapping("/findSelfScheduleByDate")
    List<Integer> findSelfScheduleByDate(@RequestParam int employeeId, @RequestParam Date start, @RequestParam Date end){
        return employeeScheduleMapper.findSelfScheduleByDate(employeeId, start, end);
    }

    @GetMapping("/findCompanyScheduleByDate")
    List<Integer> findCompanyScheduleByDate(@RequestParam int employeeId, @RequestParam Date start, @RequestParam Date end){
        return employeeScheduleMapper.findCompanyScheduleByDate(employeeId, start ,end);
    }

    @GetMapping("/findScheduleByEmployeeIdAndIsCompany")
    List<Integer> findScheduleByEmployeeIdAndIsCompany(@RequestParam int employeeId, @RequestParam int isCompany){
        return employeeScheduleMapper.findScheduleByEmployeeIdAndIsCompany(employeeId, isCompany);
    }

    @GetMapping("/findEmployeeCompanyScheduleCount")
    int findEmployeeCompanyScheduleCount(@RequestParam int employeeId, @RequestParam Date nowTime){
        return employeeScheduleMapper.findEmployeeCompanyScheduleCount(employeeId, nowTime);
    }

    @GetMapping("/findEmployeeSelfScheduleCount")
    int findEmployeeSelfScheduleCount(@RequestParam int employeeId, @RequestParam Date nowTime){
        return employeeScheduleMapper.findEmployeeSelfScheduleCount(employeeId, nowTime);
    }

    @GetMapping("/findEmployeeCompanySchedule")
    Schedule findEmployeeCompanySchedule(@RequestParam int employeeId, @RequestParam Date nowTime){
        return employeeScheduleMapper.findEmployeeCompanySchedule(employeeId, nowTime);
    }

    @GetMapping("/findEmployeeSelfSchedule")
    Schedule findEmployeeSelfSchedule(@RequestParam int employeeId, @RequestParam Date nowTime){
        return employeeScheduleMapper.findEmployeeSelfSchedule(employeeId, nowTime);
    }

}
