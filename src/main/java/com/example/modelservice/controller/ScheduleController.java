package com.example.modelservice.controller;

import com.example.modelservice.mapper.ScheduleMapper;
import com.example.modelservice.pojo.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName scheduleController
 * @Description 日程控制类
 * @Author Waynejwei
 * @LastChangeDate 2021/1/14 15:58
 * @Version v1.0
 */

@RestController
@Slf4j
public class ScheduleController {

    @Resource
    private ScheduleMapper scheduleMapper;

    @GetMapping("/findScheduleByScheduleId")
    public Schedule findScheduleByScheduleId(@RequestParam int scheduleId){
        return scheduleMapper.findScheduleByScheduleId(scheduleId);
    }

    @PostMapping("/insertSchedule")
    public int insertSchedule(@RequestBody Schedule schedule){
        return scheduleMapper.insertSchedule(schedule);
    }

    @PutMapping("/updateScheduleByScheduleId")
    public int updateScheduleByScheduleId(@RequestBody Schedule schedule){
        return scheduleMapper.updateScheduleByScheduleId(schedule);
    }

    @DeleteMapping("deleteScheduleByScheduleId")
    public int deleteScheduleByScheduleId(@RequestParam int scheduleId){
        return scheduleMapper.deleteScheduleByScheduleId(scheduleId);
    }

    @GetMapping("/findCompanyScheduleByDate")
    public int findCompanyScheduleByDate(@RequestParam Date startTime, @RequestParam Date endTime){
        return scheduleMapper.findCompanyScheduleByDate(startTime, endTime);
    }
}
