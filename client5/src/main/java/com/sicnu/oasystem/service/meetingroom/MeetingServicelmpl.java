package com.sicnu.oasystem.service.meetingroom;

import com.sicnu.oasystem.json.BackFrontMessage;
import com.sicnu.oasystem.pojo.Meeting;
import com.sicnu.oasystem.service.datarequest.MeetingData;
import com.sicnu.oasystem.service.datarequest.messageService;
import com.sicnu.oasystem.util.DataUtil;
import com.sicnu.oasystem.util.UserAuthenticationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MeetingServicelmpl
 * @Description
 * @Author pan
 * @LastChangeDate 2020/11/24 23:26
 * @Version v1.0
 */
@Service
public class MeetingServicelmpl implements MeetingService {


    @Resource
    messageService messageService;


    @Resource
    MeetingData meetingData;



    @Override
    public BackFrontMessage getAllMeetings() {
        meetingData.completeMeetings();
        List<Meeting>meetings=meetingData.getAllMeetings();
        if (meetings==null){
            return new BackFrontMessage(500,"获取所有的会议失败",null);
        }else {
            if(meetings.size()==0){
                return new BackFrontMessage(500,"没有会议",null);
            }
            return new BackFrontMessage(200,"获取所有会议成功",meetings);
        }
    }

    @Override
    public BackFrontMessage getMeetingById(Integer meetingId) {
        meetingData.completeMeetings();
        Meeting meeting=meetingData.getMeetingById(meetingId);
        if (meeting==null){
            return new BackFrontMessage(500,"获取会议失败",null);
        }else {
            return new BackFrontMessage(200,"获取会议成功",meeting);
        }
    }

    @Override
    public BackFrontMessage deleteMeeting(Integer meetingId) {
        Meeting meeting=meetingData.getMeetingById(meetingId);
        if(meeting==null){
            return new BackFrontMessage(500,"不存在会议",null);
        }
        int res = 0;
        res=meetingData.deleteMeeting(meetingId);
        if (res==0){
            return new BackFrontMessage(500,"删除会议失败",null);
        }else {
            messageService.send(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),
                    DataUtil.MESSAGE_TYPE_INFO,"删除会议","你已经成功删除预约的和会议");
            return new BackFrontMessage(200,"删除会议成功",null);
        }
    }

    @Override
    public BackFrontMessage updateOrderMeeting(Integer meetingid, Integer meetingroomid, Integer employeeid, String name, Date startTime, Date endtime, Integer peoplenum, String remark) {
        Meeting meeting=meetingData.getMeetingById(meetingid);
        if(meeting==null){
            return new BackFrontMessage(500,"修改的会议不存在",null);
        }else {
            int res=0;
            res=meetingData.updateOrderMeeting(meetingid,meetingroomid,employeeid,name,startTime,endtime,peoplenum,remark);
            if(res==0){
                return new BackFrontMessage(500,"修改会议失败",null);
            }else{
                messageService.send(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),DataUtil.MESSAGE_TYPE_INFO,"修改预约会议","你已经成功修改预约会议");
                return new BackFrontMessage(200,"修改会议成功",null);
            }
        }
    }



    @Override
    public BackFrontMessage addOrderMeeting(Integer meetingroomid, Integer employeeid, String name, Date startTime,
                                            Date endtime, Integer peoplenum, String remark) {
        if(meetingroomid==null||employeeid==null||meetingroomid<=0||employeeid<=0){
            return new BackFrontMessage(500,"预约会议失败",null);
        }
        List<Meeting> meeting=meetingData.judgeIsIsOccupy(meetingroomid,startTime,endtime);

        if (meeting!=null && meeting.size()!=0){
            return new BackFrontMessage(500,"此时段不能预约会议",null);
        }
        int res=0;
        res=meetingData.addOrderMeeting(meetingroomid,employeeid,name,startTime,endtime,peoplenum,remark);
        if(res==0){
            return new BackFrontMessage(500,"预约会议失败",null);
        }else {
            messageService.send(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),DataUtil.MESSAGE_TYPE_INFO,"预约会议","你已经成功预约会议");
            return new BackFrontMessage(200,"预约会议成功",null);

        }
    }

    @Override
    public BackFrontMessage approveMeeting(Integer meetingid, Integer appoinmentstatus) {
        if(appoinmentstatus!=1 && appoinmentstatus!=-1){
            return new BackFrontMessage(500,"会议审批失败",null);
        }else {
            int res=0;
            res=meetingData.approveMeeting(meetingid,appoinmentstatus);
            if (res==0){
                return new BackFrontMessage(500,"审批会议失败",null);
            }else {
                if(appoinmentstatus==-1){
                    messageService.send(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(), DataUtil.MESSAGE_TYPE_INFO,"会议预约审核结果","你预约的会议未被通过审核");
                }
                if(appoinmentstatus==1){
                    messageService.send(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),DataUtil.MESSAGE_TYPE_INFO,"会议预约审核结果","你预约的会议已经通过审核");
                }
                return new BackFrontMessage(200,"审批会议成功",null);
            }
        }
    }

    @Override
    public BackFrontMessage getNotApprovedMeeting() {
        List<Meeting>meetings=meetingData.waitingApproveMeeting();
        if (meetings==null){
            return new BackFrontMessage(500,"查询未审批会议失败",null);
        }else {
            if (meetings.size()==0){
                return new BackFrontMessage(500,"没有待审批的会议",null);
            }else {
                return new BackFrontMessage(200,"查询未审批会议成功",meetings);
            }
        }
    }

    @Override
    public BackFrontMessage getCurrentAllMeeting() {
        meetingData.completeMeetings();
        meetingData.completeMeetings();
        List<Meeting>meetings=meetingData.getCurrentAllMeeting();
        if(meetings==null||meetings.size()==0){
            return new BackFrontMessage(500,"当前没有会议",null);
        }else {
            return new BackFrontMessage(200,"获取当前所有会议成功",meetings);
        }
    }

    @Override
    public BackFrontMessage getAllMeetingTimeByRoomAndTime(Integer meetingroomid, Date starttime, Date endtime) {
        List<Meeting>meetings=meetingData.getAllMeetingTimeByRoomAndTime(meetingroomid,starttime,endtime);
        return new BackFrontMessage(200,"获取某一会议室莫一天的会议安排",meetings);
    }

    @Override
    public BackFrontMessage cancleApproveMeeting(Integer meetingid, Integer employeeid) {
        Meeting meeting=meetingData.getMeetingById(meetingid);
        if(meeting==null){
            return new BackFrontMessage(500,"你要取消的预约不存在",null);
        }
        int res=0;
        res=meetingData.cancleApproveMeeting(meetingid,employeeid);
        if(res==0){
            return new BackFrontMessage(500,"取消预约失败",null);
        }else{
            messageService.send(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),
                    DataUtil.MESSAGE_TYPE_INFO,"取消会议","你已经成功取消了会议:"+meeting.getName());
            return new BackFrontMessage(200,"取消预约成功",null);
        }
    }
}

