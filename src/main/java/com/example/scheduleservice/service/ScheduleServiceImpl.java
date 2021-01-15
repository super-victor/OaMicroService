package com.example.scheduleservice.service;

import com.example.scheduleservice.json.BackFrontMessage;
import com.example.scheduleservice.pojo.DataSeeAbleA;
import com.example.scheduleservice.pojo.Schedule;
import com.example.scheduleservice.request.RequestScheduleData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @ClassName ScheduleServiceImpl
 * @Description 日程管理服务的实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/19 20:32
 * @Version v1.0
 */

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    RequestScheduleData requestScheduleData;

//    @Resource
//    RequestEmployeeScheduleData requestEmployeeScheduleData;

    @Resource
    MessageService messageService;

//    @Resource
//    RequestEmployeeData requestEmployeeData;

//    @Resource
//    LogUtil logUtil;

    @Override
    public BackFrontMessage insertSelfSchedule(Schedule schedule) {
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        schedule.setLeader(currentEmployee.getEmployeeId());
//        schedule.setIsCompany(0);
//        if (isTimeCorrect(schedule)){
//            return new BackFrontMessage(500,"结束时间小于开始时间",null);
//        }
//        try{
//            int result = requestScheduleData.insertSchedule(schedule);
//            if (result <= 0){
////                logUtil.customException("添加个人日程失败");
//                return new BackFrontMessage(500,"个人日程添加失败",null);
//            }else{
//                EmployeeSchedule employeeSchedule = new EmployeeSchedule();
//                employeeSchedule.setScheduleId(schedule.getScheduleId());
//                employeeSchedule.setEmployeeId(currentEmployee.getEmployeeId());
//                int result2 = requestEmployeeScheduleData.insertEmployeeSchedule(employeeSchedule);
//                if (result2 <= 0){
////                    logUtil.customException("添加职工日程映射失败,原因是添加职工日程映射失败");
//                    throw new Exception("添加员工日程映射失败");
//                }
////                logUtil.insertInfo("添加个人日程成功，日程信息为："+schedule);
//                return new BackFrontMessage(200,"添加个人日程成功",schedule.getScheduleId());
//            }
//        } catch (Exception e){
//            return new BackFrontMessage(500,"添加员工日程映射失败",null);
//        }
        return null;
    }

    @Override
    public BackFrontMessage insertCompanySchedule(Schedule schedule) {
//        List<Integer> joinerList = new ArrayList<>();
//        //检查是否有重复的参与者。若有重复的，则自动去重。
//        for (Integer join : schedule.getJoiner()) {
//            if (!joinerList.contains(join)) {
//                joinerList.add(join);
//            }
//        }
//        //判断设置的leader在不在参与人列表里面。如果不在则自动在列表中添加上
//        if (!joinerList.contains(schedule.getLeader())) {
//            joinerList.add(schedule.getLeader());
//        }
//        schedule.setIsCompany(1);
//        if (isTimeCorrect(schedule)){
//            return new BackFrontMessage(500,"结束时间小于开始时间",null);
//        }
//        try{
//            int result = requestScheduleData.insertSchedule(schedule);
//            if (result <= 0){
////                logUtil.customException("日程管理员添加公司日程失败");
//                return new BackFrontMessage(500,"添加日程失败",null);
//            }else{
//                //将参与者加入职工日程对应表
//                for (Integer employeeId : joinerList) {
//                    EmployeeSchedule employeeSchedule = new EmployeeSchedule();
//                    employeeSchedule.setEmployeeId(employeeId);
//                    employeeSchedule.setScheduleId(schedule.getScheduleId());
//                    int result2 = requestEmployeeScheduleData.insertEmployeeSchedule(employeeSchedule);
//                    if (result2 <= 0){
////                        logUtil.customException("日程管理员添加公司日程失败，原因是添加职工日程映射失败");
//                        throw new Exception("添加职工日程映射失败");
//                    }
//                }
//                for (Integer employeeId : joinerList) {
//                    //添加完了才能发送消息，免得失败后事务回滚
//                    messageService.send(employeeId, DataUtil.MESSAGE_TYPE_INFO, DataUtil.MESSAGE_TITLE_SCHEDULE, "您收到了一个关于'"+schedule.getContent()+"'的公司日程");
//                }
////                logUtil.customExceptiontionception("日程管理员添加公司日程成功，日程信息："+schedule);
//                return new BackFrontMessage(200,"添加日程成功",schedule.getScheduleId());
//            }
//        } catch (Exception e){
//            //事务未完成，则回滚
//            return new BackFrontMessage(500,"添加职工日程映射失败",null);
//        }
        return null;
    }

    @Override
    public BackFrontMessage updateScheduleByScheduleId(Schedule schedule, int scheduleId, int isCompany) {
//        schedule.setScheduleId(scheduleId);
//        if (isTimeCorrect(schedule)){
//            return new BackFrontMessage(500,"结束时间小于开始时间",null);
//        }
//        int employeeId = UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId();
//        //如果是公司日程，则需要判断权限
//        if (isCompany == 1){
//            //判断是否为日程管理员或者日程领导者。若都不是，则无权修改
//            int isScheduleManager = requestEmployeeData.findRoleByEmployeeIdAndRoleId(
//                    employeeId, 4);
//            if (isScheduleManager == 0 && dontHasRoleToManageSchedule(scheduleId)){
//                return new BackFrontMessage(500,"您无权修改公司日程",null);
//            }
//            if (schedule.getLeader() != null){
//                EmployeeSchedule es = requestEmployeeScheduleData
//                        .findEmployeeScheduleByEmployeeIdAndScheduleId(schedule.getLeader(), scheduleId);
//                if (es == null) {
//                    return new BackFrontMessage(500,"此人没有加入到日程中，不能转交管理权！",null);
//                }
//            }
//        }
//        if (isCompany == 0){
//            schedule.setLeader(null);  //个人日程的领导者默认为自己，不能修改
//        }
//        int result = requestScheduleData.updateScheduleByScheduleId(schedule);
//        if (result <= 0){
////            logUtil.updateInfo("修改日程失败");
//            return new BackFrontMessage(500,"修改日程失败",null);
//        }else{
////            logUtil.updateInfo("修改日程成功，修改内容为："+schedule);
//            return new BackFrontMessage(200,"修改日程成功",null);
//        }
        return null;
    }

    @Override
    public BackFrontMessage deleteScheduleByScheduleId(int scheduleId, int isCompany) {
//        Integer employeeId = UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId();
//        //个人日程只有自己才可以删除，公司日程管理员才可以删除
//        if (isCompany == 0){
//            EmployeeSchedule employeeSchedule = requestEmployeeScheduleData.findEmployeeScheduleByEmployeeIdAndScheduleId(
//                    employeeId, scheduleId);
//            if (employeeSchedule == null){
//                return new BackFrontMessage(500,"无权删除此日程",null);
//            }
//        }
//        List<Integer> employeeIdList = requestEmployeeScheduleData.findEmployeeScheduleByScheduleId(scheduleId);
//        try{
//            //删除日程前，需要删除职工日程映射
//            int result1 = requestEmployeeScheduleData.deleteEmployeeScheduleByScheduleId(scheduleId);
//            if (result1 <= 0){
////                logUtil.deleteInfo("删除日程失败，日程id为："+scheduleId+"，原因是删除职工日程映射失败");
//                return new BackFrontMessage(500,"删除职工日程映射失败",null);
//            }else{
//                int result2 = requestScheduleData.deleteScheduleByScheduleId(scheduleId);
//                if (result2 <= 0){
////                    logUtil.deleteInfo("删除日程失败，日程id为："+scheduleId);
//                    throw new Exception("删除日程失败");
//                }else{
//                    if (isCompany == 1){
//                        //删除日程后，通知职工日程结束
//                        for (Integer employeeId1 : employeeIdList) {
//                            String content = requestScheduleData.findScheduleByScheduleId(scheduleId).getContent();
//                            messageService.send(employeeId1, DataUtil.MESSAGE_TYPE_INFO, DataUtil.MESSAGE_TITLE_SCHEDULE, "有关'"+content+"'内容的公司日程已经结束");
//                        }
//                    }
////                    logUtil.deleteInfo("删除日程成功，日程id为："+scheduleId);
//                    return new BackFrontMessage(200,"删除日程成功",null);
//                }
//            }
//        } catch (Exception e){
//            return new BackFrontMessage(500,"删除日程失败",null);
//        }
        return null;
    }

    @Override
    public BackFrontMessage findScheduleByScheduleId(int scheduleId) {
//        Map<String,Object> map = new HashMap<>(16);
//        Schedule schedule = requestScheduleData.findScheduleByScheduleId(scheduleId);
//        if (schedule == null) return new BackFrontMessage(500,"没有此日程",null);
//        map.put("startTime",schedule.getStartTime());
//        map.put("endTime",schedule.getEndTime());
//        map.put("content",schedule.getContent());
//        map.put("location",schedule.getLocation());
//        map.put("remark",schedule.getRemark());
//        map.put("type",schedule.getType());
//        if (schedule.getIsCompany() == 1){
//            map.put("leader", requestEmployeeData.findEmployeeByEmployeeId(schedule.getLeader()).getName());
//            List<Integer> joinerList = requestEmployeeScheduleData.findEmployeeScheduleByScheduleId(scheduleId);
//            log.info("joinerList --> "+joinerList);
//            List<String> joiner = new ArrayList<>();
//            for (Integer employeeId : joinerList) {
//                Employee employee = requestEmployeeData.findEmployeeByEmployeeId(employeeId);
//                joiner.add(employee.getName());
//            }
//            map.put("joiner",joiner);
//        }
//        return new BackFrontMessage(200,"查找日成功",map);
        return null;
    }

    @Override
    public List<DataSeeAbleA> findCompanyScheduleNumber() {
        // 获取今天凌晨的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dayTimeStr = sdf.format(date) + " 00:00:00";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dayTime = null;
        try {
            dayTime = sdf1.parse(dayTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = new GregorianCalendar();
        assert dayTime != null;
        calendar.setTime(dayTime);
        Date nextDate;
        List<DataSeeAbleA> countList = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            nextDate=calendar.getTime();
            int count = requestScheduleData.findCompanyScheduleByStartTimeAndEndTime(nextDate, dayTime);
            DataSeeAbleA dataSeeAbleA = new DataSeeAbleA(nextDate, count);
            countList.add(dataSeeAbleA);
            dayTime = nextDate;
            calendar.add(Calendar.DATE, -1); //把日期往前推动一天
        }
        return countList;
    }

    /**
     * @MethodName hasRoleToManageSchedule
     * @param scheduleId 日程id
     * @Description 是否无权操作日程，没有权限返回true
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/19
     */
    private boolean dontHasRoleToManageSchedule(int scheduleId){
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        Schedule oldSchedule = requestScheduleData.findScheduleByScheduleId(scheduleId);
//        return !currentEmployee.getEmployeeId().equals(oldSchedule.getLeader());
        return false;
    }

    /**
     * @MethodName isTimeCorrect
     * @param schedule 日程
     * @Description 查看日程结束时间是都小于开始时间，是返回true
     * @Author Waynejwei
     * @Return boolean
     * @LastChangeDate 2020/11/19
     */
    private boolean isTimeCorrect(Schedule schedule){
        if (schedule.getScheduleId() != null){  //修改时的判断时间是否合理
            if (schedule.getStartTime() == null){
                Schedule nowSchedule = requestScheduleData.findScheduleByScheduleId(schedule.getScheduleId());
                if (schedule.getEndTime() == null){   //都为空，不修改时间
                    return false;
                }else{
                    return schedule.getEndTime().compareTo(nowSchedule.getStartTime()) < 0;
                }
            }else{
                if (schedule.getEndTime() == null){
                    Schedule nowSchedule = requestScheduleData.findScheduleByScheduleId(schedule.getScheduleId());
                    return nowSchedule.getEndTime().compareTo(schedule.getStartTime()) < 0;
                }else{
                    return schedule.getEndTime().compareTo(schedule.getStartTime()) < 0;
                }
            }
        }else{  //增加时判断时间是否合理
            if (schedule.getStartTime() != null && schedule.getEndTime() != null){
                return schedule.getEndTime().compareTo(schedule.getStartTime()) < 0;
            }else{
                return true;
            }
        }
    }
}
