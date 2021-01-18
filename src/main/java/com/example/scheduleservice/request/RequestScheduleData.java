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

    /**
     * @MethodName findScheduleByScheduleId
     * @param scheduleId 日程Id
     * @Description 查找日程
     * @Author Waynejwei
     * @Return com.example.scheduleservice.pojo.Schedule
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findScheduleByScheduleId")
    Schedule findScheduleByScheduleId(@RequestParam int scheduleId);

    /**
     * @MethodName insertSchedule
     * @param schedule 日程
     * @Description 添加日程
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2021/1/18
     */
    @PostMapping("/insertSchedule")
    int insertSchedule(@RequestBody Schedule schedule);

    /**
     * @MethodName updateScheduleByScheduleId
     * @param schedule 日程
     * @Description 修改日程
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2021/1/18
     */
    @PutMapping("/updateScheduleByScheduleId")
    int updateScheduleByScheduleId(@RequestBody Schedule schedule);

    /**
     * @MethodName deleteScheduleByScheduleId
     * @param scheduleId 日程Id
     * @Description 删除日程
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2021/1/18
     */
    @DeleteMapping("deleteScheduleByScheduleId")
    int deleteScheduleByScheduleId(@RequestParam int scheduleId);

    /**
     * @MethodName findCompanyScheduleByStartTimeAndEndTime
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @Description 获取某一时段的日程
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findCompanyScheduleByStartTimeAndEndTime")
    int findCompanyScheduleByStartTimeAndEndTime(@RequestParam Date startTime, @RequestParam Date endTime);

}