package com.example.scheduleservice;

import com.example.scheduleservice.pojo.EmployeeSchedule;
import com.example.scheduleservice.pojo.Schedule;
import com.example.scheduleservice.request.RequestEmployeeScheduleData;
import com.example.scheduleservice.request.RequestScheduleData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class ScheduleServiceApplicationTests {

    @Resource
    private RequestScheduleData requestScheduleData;

    @Resource
    private RequestEmployeeScheduleData requestEmployeeScheduleData;

    private Date st;
    private Date ed;

    @Test
    void contextLoads() {
        System.out.println(requestScheduleData.findScheduleByScheduleId(1));
    }

    @Test
    void insertScheduleId(){
        Schedule schedule = new Schedule();
        schedule.setContent("微服务测试");
        String startTime = "2020-01-13 08:41:00";
        String endTime = "2020-01-14 16:30:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            schedule.setEndTime(simpleDateFormat.parse(endTime));
            schedule.setStartTime(simpleDateFormat.parse(startTime));
        } catch (ParseException e) {
            System.out.println("时间格式错误");
        }
        schedule.setLocation("实验室");
        schedule.setIsCompany(1);
        schedule.setLeader(1);
        int result = requestScheduleData.insertSchedule(schedule);
        System.out.println(result);
    }

    @Test
    void updateSchedule(){
        Schedule schedule = new Schedule();
        schedule.setScheduleId(27);
        schedule.setContent("实现微服务");
        String endTime = "2020-01-18 16:30:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            schedule.setEndTime(simpleDateFormat.parse(endTime));
        } catch (ParseException e) {
            System.out.println("时间格式错误");
        }
        System.out.println(requestScheduleData.updateScheduleByScheduleId(schedule));
    }

    @Test
    void deleteSchedule(){
        System.out.println(requestScheduleData.deleteScheduleByScheduleId(28));
    }

    @Test
    void findCompanyScheduleByDate(){
        String startTime = "2020-01-12 08:41:00";
        String endTime = "2021-01-13 16:30:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            st = simpleDateFormat.parse(startTime);
            ed = simpleDateFormat.parse(endTime);
        } catch (ParseException e) {
            System.out.println("时间格式错误");
        }
        System.out.println(requestScheduleData.findCompanyScheduleByStartTimeAndEndTime(st, ed));
    }

    @Test
    void insertEmployeeSchedule(){
        EmployeeSchedule employeeSchedule = new EmployeeSchedule();
        employeeSchedule.setEmployeeId(2);
        employeeSchedule.setScheduleId(29);
        System.out.println(requestEmployeeScheduleData.insertEmployeeSchedule(employeeSchedule));
    }

    @Test
    void deleteEmployeeSchedule(){
        System.out.println(requestEmployeeScheduleData.deleteEmployeeScheduleByScheduleId(41));
    }

    @Test
    void findEmployeeScheduleByScheduleId(){
        System.out.println(requestEmployeeScheduleData.findEmployeeScheduleByScheduleId(29));
    }



}
