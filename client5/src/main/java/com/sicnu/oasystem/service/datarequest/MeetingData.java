package com.sicnu.oasystem.service.datarequest;

import com.sicnu.oasystem.pojo.Meeting;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName MeetingData
 * @Description
 * @Author pan
 * @LastChangeDate 2021/1/15 10:12
 * @Version v1.0
 */
@Service
@FeignClient("MODEL-SERVICE")
public interface MeetingData {
    @GetMapping("/getAllMeetings")
    List<Meeting> getAllMeetings();

    @GetMapping("/completeMeetings")
    Integer completeMeetings();

    @GetMapping("/getCurrentAllMeeting")
    List<Meeting>getCurrentAllMeeting();

    @GetMapping("/getMeetingById")
    Meeting getMeetingById(@RequestParam("meetingId") Integer meetingId);


    @DeleteMapping("/deleteMeeting")
    Integer deleteMeeting(@RequestParam("meetingId") Integer meetingId);

    @PostMapping("/addOrderMeeting")
    Integer addOrderMeeting(@RequestParam("meetingroomid") Integer meetingroomid,
                            @RequestParam("employeeid") Integer employeeid,
                            @RequestParam("name") String name,
                            @RequestParam("startTime") Date startTime,
                            @RequestParam("endtime") Date endtime,
                            @RequestParam("peoplenum") Integer peoplenum,
                            @RequestParam("remark") String remark);

    @PostMapping("/approveMeeting")
    Integer approveMeeting(@RequestParam("meeting") Integer meeting,
                           @RequestParam("appoinmentstatus") Integer appoinmentstatus);

    @PutMapping("/updateOrderMeeting")
    Integer updateOrderMeeting(@RequestParam("meetingid") Integer meetingid,
                               @RequestParam("meetingroomid") Integer meetingroomid,
                               @RequestParam("employeeid") Integer employeeid,
                               @RequestParam("name") String name,
                               @RequestParam("startTime") Date startTime,
                               @RequestParam("endtime") Date endtime,
                               @RequestParam("peoplenum") Integer peoplenum,
                               @RequestParam("remark") String remark);

    @GetMapping("/waitingApproveMeeting")
    List<Meeting> waitingApproveMeeting();

    @GetMapping("/judgeIsIsOccupy")
    List<Meeting> judgeIsIsOccupy(@RequestParam("meetingroomId") Integer meetingroomId,
                                  @RequestParam("startTime") Date startTime,
                                  @RequestParam("endTime") Date endTime);

    @GetMapping("/getAllMeetingTimeByRoomAndTime")
    List<Meeting> getAllMeetingTimeByRoomAndTime(@RequestParam("meetingrommid") Integer meetingrommid,
                                                 @RequestParam("stratTime") Date stratTime,
                                                 @RequestParam("endTime") Date endTime);

    @DeleteMapping("/cancleApproveMeeting")
    Integer  cancleApproveMeeting(@RequestParam("meetingid") Integer meetingid,
                                  @RequestParam("employeeid") Integer employeeid);

    @GetMapping("/getMeetingTrend")
    List<Map<String,Object>> getMeetingTrend(@RequestParam("departmentName") String departmentName);

    @GetMapping("/getMeetingTrendnums")
    Integer getMeetingTrendnums(@RequestParam("departmentName") String departmentName);
}
