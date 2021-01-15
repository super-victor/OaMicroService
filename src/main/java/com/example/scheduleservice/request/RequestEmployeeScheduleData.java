package com.example.scheduleservice.request;

import com.example.scheduleservice.pojo.EmployeeSchedule;
import com.example.scheduleservice.pojo.Schedule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @InterfaceName RequestEmployeeScheduleData
 * @Description 请求职工日程表
 * @Author Waynejwei
 * @LastChangeDate 2021/1/14 19:22
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface RequestEmployeeScheduleData {

    @PostMapping("/insertEmployeeSchedule")
    int insertEmployeeSchedule(@RequestBody EmployeeSchedule employeeSchedule);

    @DeleteMapping("/deleteEmployeeScheduleByScheduleId")
    int deleteEmployeeScheduleByScheduleId(@RequestParam int scheduleId);

    @GetMapping("/findEmployeeScheduleByScheduleId")
    List<Integer> findEmployeeScheduleByScheduleId(@RequestParam int scheduleId);

    @GetMapping("/findEmployeeScheduleByEmployeeId")
    List<Integer> findEmployeeScheduleByEmployeeId(@RequestParam int employeeId);

    @GetMapping("/findEmployeeScheduleByEmployeeIdAndScheduleId")
    EmployeeSchedule findEmployeeScheduleByEmployeeIdAndScheduleId(@RequestParam int employeeId, @RequestParam int scheduleId);

    @GetMapping("/findSelfScheduleByDate")
    List<Integer> findSelfScheduleByDate(@RequestParam int employeeId, @RequestParam Date start, @RequestParam Date end);

    @GetMapping("/findCompanyScheduleByDate")
    List<Integer> findCompanyScheduleByDate(@RequestParam int employeeId, @RequestParam Date start, @RequestParam Date end);

    @GetMapping("/findScheduleByEmployeeIdAndIsCompany")
    List<Integer> findScheduleByEmployeeIdAndIsCompany(@RequestParam int employeeId, @RequestParam int isCompany);

    @GetMapping("/findEmployeeCompanyScheduleCount")
    int findEmployeeCompanyScheduleCount(@RequestParam int employeeId, @RequestParam Date nowTime);

    @GetMapping("/findEmployeeSelfScheduleCount")
    int findEmployeeSelfScheduleCount(@RequestParam int employeeId, @RequestParam Date nowTime);

    @GetMapping("/findEmployeeCompanySchedule")
    Schedule findEmployeeCompanySchedule(@RequestParam int employeeId, @RequestParam Date nowTime);

    @GetMapping("/findEmployeeSelfSchedule")
    Schedule findEmployeeSelfSchedule(@RequestParam int employeeId, @RequestParam Date nowTime);
}