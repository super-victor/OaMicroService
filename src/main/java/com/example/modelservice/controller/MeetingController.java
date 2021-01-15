package com.example.modelservice.controller;

import com.example.modelservice.mapper.MeetingMapper;
import com.example.modelservice.pojo.Meeting;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName meetingController
 * @Description 会议的控制器
 * @Author pan
 * @LastChangeDate 2021/1/14 19:20
 * @Version v1.0
 */
@RestController
public class MeetingController {

    @Resource
    MeetingMapper meetingMapper;

    @GetMapping("/getAllMeetings")
    List<Meeting> getAllMeetings(){
        return meetingMapper.getAllMeetings();
    }


    @GetMapping("/completeMeetings")
    Integer completeMeetings(){
        return meetingMapper.completeMeetings();
    }

    @GetMapping("/getCurrentAllMeeting")
    List<Meeting>getCurrentAllMeeting(){
        return meetingMapper.getCurrentAllMeeting();
    }

    @GetMapping("/getMeetingById")
    Meeting getMeetingById(@RequestParam Integer meetingId){
        return meetingMapper.getMeetingById(meetingId);
    }


    @DeleteMapping("/deleteMeeting")
    Integer deleteMeeting(@RequestParam Integer meetingId){
        return meetingMapper.deleteMeeting(meetingId);
    }

    @PostMapping("/addOrderMeeting")
    Integer addOrderMeeting(@RequestParam Integer meetingroomid,
                            @RequestParam Integer employeeid,
                            @RequestParam String name,
                            @RequestParam Date startTime,
                            @RequestParam Date endtime,
                            @RequestParam Integer peoplenum,
                            @RequestParam String remark){
        return meetingMapper.addOrderMeeting(meetingroomid,employeeid,name,startTime,endtime,peoplenum,remark);
    }

    @PostMapping("/approveMeeting")
    Integer approveMeeting(@RequestParam Integer meeting,
                           @RequestParam Integer appoinmentstatus){
        return meetingMapper.approveMeeting(meeting,appoinmentstatus);
    }

    @PutMapping("/updateOrderMeeting")
    Integer updateOrderMeeting(@RequestParam Integer meetingid,
                               @RequestParam Integer meetingroomid,
                               @RequestParam Integer employeeid,
                               @RequestParam String name,
                               @RequestParam Date startTime,
                               @RequestParam Date endtime,
                               @RequestParam Integer peoplenum,
                               @RequestParam String remark){
       return meetingMapper.updateOrderMeeting(meetingid,meetingroomid,employeeid,name,startTime,endtime,peoplenum,remark);
    }

    @GetMapping("/waitingApproveMeeting")
    List<Meeting> waitingApproveMeeting(){
        return meetingMapper.waitingApproveMeeting();
    }

    @GetMapping("/judgeIsIsOccupy")
    List<Meeting> judgeIsIsOccupy(@RequestParam Integer meetingroomId,
                                  @RequestParam Date startTime,
                                  @RequestParam Date endTime){
        return meetingMapper.judgeIsIsOccupy(meetingroomId,startTime,endTime);
    }

    @GetMapping("/getAllMeetingTimeByRoomAndTime")
    List<Meeting> getAllMeetingTimeByRoomAndTime(@RequestParam Integer meetingrommid,
                                                 @RequestParam Date stratTime,
                                                 @RequestParam Date endTime){
        return meetingMapper.getAllMeetingTimeByRoomAndTime(meetingrommid,stratTime,endTime);
    }

    @DeleteMapping("/cancleApproveMeeting")
    Integer  cancleApproveMeeting(@RequestParam Integer meetingid,
                                  @RequestParam Integer employeeid){
       return meetingMapper.cancleApproveMeeting(meetingid,employeeid);
    }

    @GetMapping("/getMeetingTrend")
    List<Map<String,Object>>getMeetingTrend(@RequestParam String departmentName){
       return meetingMapper.getMeetingTrend(departmentName);
    }

    @GetMapping("/getMeetingTrendnums")
    Integer getMeetingTrendnums(@RequestParam String departmentName){
        return meetingMapper.getMeetingTrendnums(departmentName);
    }
}

