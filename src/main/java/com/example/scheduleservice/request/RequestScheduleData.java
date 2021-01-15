package com.example.scheduleservice.request;

import com.example.scheduleservice.pojo.Schedule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @InterfaceName requestDaoData
 * @Description 添加描述
 * @Author Waynejwei
 * @LastChangeDate 2021/1/14 16:24
 * @Version v1.0
 */

@FeignClient("model-service")
@Service
public interface RequestScheduleData {

    @GetMapping("/findScheduleByScheduleId")
    Schedule findScheduleByScheduleId(@RequestParam int scheduleId);

    @PostMapping("/insertSchedule")
    int insertSchedule(@RequestBody Schedule schedule);

    @PutMapping("/updateScheduleByScheduleId")
    int updateScheduleByScheduleId(@RequestBody Schedule schedule);

    @DeleteMapping("deleteScheduleByScheduleId")
    int deleteScheduleByScheduleId(@RequestParam int scheduleId);

    @GetMapping("/findCompanyScheduleByStartTimeAndEndTime")
    int findCompanyScheduleByStartTimeAndEndTime(@RequestParam Date startTime, @RequestParam Date endTime);

}