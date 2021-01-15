package com.example.modelservice.controller;

import com.example.modelservice.mapper.MeetingRoomMapper;
import com.example.modelservice.pojo.MeetingRoom;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.PUT;
import java.util.List;

/**
 * @ClassName meetingRoomController
 * @Description
 * @Author pan
 * @LastChangeDate 2021/1/14 19:36
 * @Version v1.0
 */
@RestController
public class MeetingRoomController {

    @Resource
    MeetingRoomMapper meetingRoomMapper;
    /**
     * @MethodName getAllMeetingRoom
     * @param place 地点
     * @param isOccapy 是否被占用
     * @param MeetingRoomName 会议室名称
     * @Description 获取所有的会议室信息
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.MeetingRoom>
     * @LastChangeDate 2020/12/10
     */
    @GetMapping("/getAllMeetingRoom")
    List<MeetingRoom> getAllMeetingRoom(@RequestParam(required = false) String place,
                                        @RequestParam(required = false) Integer isOccapy,
                                        @RequestParam(required = false) String MeetingRoomName){
        return meetingRoomMapper.getAllMeetingRoom(place,isOccapy,MeetingRoomName);
    }

    /**
     * @MethodName getMeetingRoomNum
     * @param
     * @Description 获取会议室总数
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    @GetMapping("/getMeetingRoomNum")
    Integer getMeetingRoomNum(){
        return meetingRoomMapper.getMeetingRoomNum();
    }

    /**
     * @MethodName updateMeetingRoomIsOccapy
     * @param
     * @Description 修改会议室占用情况
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    @PutMapping("/updateMeetingRoomIsOccapy")
    Integer updateMeetingRoomIsOccapy(){
        return meetingRoomMapper.updateMeetingRoomIsOccapy();
    }

    /**
     * @MethodName updateMettingRoomNotIsOccapy
     * @param
     * @Description 修改会议室占用情况
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    @PutMapping("/updateMettingRoomNotIsOccapy")
    Integer updateMettingRoomNotIsOccapy(){
        return meetingRoomMapper.updateMettingRoomNotIsOccapy();
    }

    /**
     * @MethodName getMeetingRoomById
     * @param MeetingRoomId 会议室Id
     * @Description 通过会议室Id获取会议室信息
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.MeetingRoom
     * @LastChangeDate 2020/12/10
     */
    @GetMapping("/getMeetingRoomById")
    MeetingRoom getMeetingRoomById(@RequestParam Integer MeetingRoomId){
        return meetingRoomMapper.getMeetingRoomById(MeetingRoomId);
    }

    /**
     * @MethodName addMeetingRoom
     * @param name 会议室民成功
     * @param place 地点
     * @param isOccapy 是否被占用
     * @param maxperson 最大容纳人数
     * @Description 添加会议室
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    @PostMapping("/addMeetingRoom")
    Integer addMeetingRoom(@RequestParam String name,
                           @RequestParam String place,
                           @RequestParam Integer isOccapy,
                           @RequestParam Integer maxperson){
        return meetingRoomMapper.addMeetingRoom(name,place,isOccapy,maxperson);
    }

    /**
     * @MethodName updateMeetingRoom
     * @param MeetingRoomId 会议室Id
     * @param place 地点
     * @param isOccapy 是否被占用
     * @param MeetingRoomName 会议室名称
     * @param maxpserson 最大容纳人数
     * @Description 修改会议室信息
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    @PutMapping("/updateMeetingRoom")
    Integer updateMeetingRoom(@RequestParam Integer MeetingRoomId,
                              @RequestParam(required = false) String place,
                              @RequestParam(required = false) Integer isOccapy,
                              @RequestParam(required = false) String MeetingRoomName,
                              @RequestParam(required = false) int maxpserson){
        return meetingRoomMapper.updateMeetingRoom(MeetingRoomId,place,isOccapy,MeetingRoomName,maxpserson);
    }

    /**
     * @MethodName deleteMeetingRoom
     * @param MeetingRoomId
     * @Description 添加描述
     * @Author pan
     * @Return java.lang.Integer
     * @LastChangeDate 2020/12/10
     */
    @DeleteMapping("/deleteMeetingRoom")
    Integer deleteMeetingRoom(@RequestParam Integer MeetingRoomId){
        return meetingRoomMapper.deleteMeetingRoom(MeetingRoomId);
    }

    /**
     * @MethodName getMeetingRoomByNameAndPlace
     * @param name
     * @param place
     * @Description 添加描述
     * @Author pan
     * @Return com.sicnu.oasystem.pojo.MeetingRoom
     * @LastChangeDate 2020/12/10
     */
    @GetMapping("/getMeetingRoomByNameAndPlace")
    MeetingRoom getMeetingRoomByNameAndPlace(@RequestParam String name, @RequestParam String place){
        return meetingRoomMapper.getMeetingRoomByNameAndPlace(name,place);
    }

    /**
     * @MethodName getAllStorey
     * @param
     * @Description 添加描述
     * @Author pan
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2020/12/10
     */
    @GetMapping("/getAllStorey")
    List<String>getAllStorey(){
        return meetingRoomMapper.getAllStorey();
    }

    /**
     * @MethodName getMeetinRoomByStorey
     * @param place
     * @Description 添加描述
     * @Author pan
     * @Return java.util.List<com.sicnu.oasystem.pojo.MeetingRoom>
     * @LastChangeDate 2020/12/10
     */
    @GetMapping("/getMeetinRoomByStorey")
    List<MeetingRoom>getMeetinRoomByStorey(@RequestParam String place){
        return meetingRoomMapper.getMeetinRoomByStorey(place);
    }
}

