package com.sicnu.oasystem.service.datarequest;

import com.sicnu.oasystem.pojo.MeetingRoom;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @InterfaceName MeetingRoomData
 * @Description
 * @Author pan
 * @LastChangeDate 2021/1/15 10:11
 * @Version v1.0
 */
@Service
@FeignClient("MODEL-SERVICE")
public interface MeetingRoomData {
    @GetMapping("/getAllMeetingRoom")
    List<MeetingRoom> getAllMeetingRoom(@RequestParam(value = "place",required = false) String place,
                                        @RequestParam(value = "isOccapy",required = false) Integer isOccapy,
                                        @RequestParam(value = "MeetingRoomName",required = false) String MeetingRoomName);


    @GetMapping("/getMeetingRoomNum")
    Integer getMeetingRoomNum();


    @PutMapping("/updateMeetingRoomIsOccapy")
    Integer updateMeetingRoomIsOccapy();


    @PutMapping("/updateMettingRoomNotIsOccapy")
    Integer updateMettingRoomNotIsOccapy();



    @GetMapping("/getMeetingRoomById")
    MeetingRoom getMeetingRoomById(@RequestParam("MeetingRoomId") Integer MeetingRoomId);


    @PostMapping("/addMeetingRoom")
    Integer addMeetingRoom(@RequestParam("name") String name,
                           @RequestParam("place") String place,
                           @RequestParam("isOccapy") Integer isOccapy,
                           @RequestParam("maxperson") Integer maxperson);


    @PutMapping("/updateMeetingRoom")
    Integer updateMeetingRoom(@RequestParam("MeetingRoomId") Integer MeetingRoomId,
                              @RequestParam("place") String place,
                              @RequestParam("isOccapy") Integer isOccapy,
                              @RequestParam("MeetingRoomName") String MeetingRoomName,
                              @RequestParam("maxpserson") int maxpserson);


    @DeleteMapping("/deleteMeetingRoom")
    Integer deleteMeetingRoom(@RequestParam("MeetingRoomId") Integer MeetingRoomId);

    @GetMapping("/getMeetingRoomByNameAndPlace")
    MeetingRoom getMeetingRoomByNameAndPlace(@RequestParam("name") String name, @RequestParam("place") String place);

    @GetMapping("/getAllStorey")
    List<String> getAllStorey();

    @GetMapping("/getMeetinRoomByStorey")
    List<MeetingRoom>getMeetinRoomByStorey(@RequestParam("place") String place);
}
