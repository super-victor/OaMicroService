package com.example.scheduleservice.service;

import com.example.scheduleservice.json.BackFrontMessage;
import com.example.scheduleservice.request.RequestScheduleData;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName EmployeeScheduleServiceImpl
 * @Description 职工日程对应服务的实现类
 * @Author Waynejwei
 * @LastChangeDate 2020/11/20 15:37
 * @Version v1.0
 */

@Service
@Slf4j
public class EmployeeScheduleServiceImpl implements EmployeeScheduleService {

//    @Resource
//    RequestEmployeeScheduleData requestEmployeeScheduleData;

    @Resource
    RequestScheduleData requestScheduleData;

//    @Resource
//    RequestEmployeeData requestEmployeeData;

    @Override
    public BackFrontMessage findEmployeeScheduleByEmployeeId() {
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        List<Integer> scheduleIdList = requestEmployeeScheduleData
//                .findEmployeeScheduleByEmployeeId(currentEmployee.getEmployeeId());
//        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
        return null;
    }

    @Override
    public BackFrontMessage findSelfScheduleByDate(Date start, Date end) {
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        List<Integer> scheduleIdList = requestEmployeeScheduleData
//                .findSelfScheduleByDate(currentEmployee.getEmployeeId(), start, end);
//        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
        return null;
    }



    @Override
    public BackFrontMessage findCompanyScheduleByDate(Date start, Date end) {
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        List<Integer> scheduleIdList = requestEmployeeScheduleData
//                .findCompanyScheduleByDate(currentEmployee.getEmployeeId(), start, end);
//        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
        return null;
    }

    @Override
    public BackFrontMessage findSelfScheduleByEmployeeId() {
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        List<Integer> scheduleIdList = requestEmployeeScheduleData
//                .findScheduleByEmployeeIdAndIsCompany(currentEmployee.getEmployeeId(), 0);
//        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
        return null;
    }

    @Override
    public BackFrontMessage findCompanyScheduleByEmployeeId() {
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        List<Integer> scheduleIdList = requestEmployeeScheduleData
//                .findScheduleByEmployeeIdAndIsCompany(currentEmployee.getEmployeeId(), 1);
//        return new BackFrontMessage(200,"查询成功",getScheduleListByEmployeeSchduleList(scheduleIdList));
        return null;
    }

    @Override
    public BackFrontMessage findEmployeeScheduleByScheduleId(int scheduleId) {
//        List<Integer> employeeIdList = requestEmployeeScheduleData
//                .findEmployeeScheduleByScheduleId(scheduleId);
//        List<Employee> employeeList = new ArrayList<>();
//        for (Integer employeeId : employeeIdList) {
//            Employee employee = requestEmployeeData.findEmployeeByEmployeeId(employeeId);
//            employeeList.add(employee);
//        }
//        return new BackFrontMessage(200,"查找成功",employeeList);
        return null;
    }

    @Override
    public BackFrontMessage findEmployeeScheduleCount() {
//        Date date = new Date();
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        int companyScheduleCount = requestEmployeeScheduleData
//                .findEmployeeCompanyScheduleCount(currentEmployee.getEmployeeId(), date);
//        int selfScheduleCount = requestEmployeeScheduleData
//                .findEmployeeSelfScheduleCount(currentEmployee.getEmployeeId(), date);
//        Map<String, Integer> map = new HashMap<>();
//        map.put("companyScheduleCount", companyScheduleCount);
//        map.put("selfScheduleCount", selfScheduleCount);
//        return new BackFrontMessage(200, null, map);
        return null;
    }

    @Override
    public BackFrontMessage findEmployeeScheduleNotStart() {
//        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
//        Date date = new Date();
//        Schedule companySchedule = requestEmployeeScheduleData
//                .findEmployeeCompanySchedule(currentEmployee.getEmployeeId(), date);
//        Schedule selfSchedule = requestEmployeeScheduleData
//                .findEmployeeSelfSchedule(currentEmployee.getEmployeeId(), date);
//        Map<String, Schedule> map = new HashMap<>();
//        map.put("companySchedule", companySchedule);
//        map.put("selfSchedule", selfSchedule);
//        return new BackFrontMessage(200, null, map);
        return null;
    }

    /**
     * @MethodName getScheduleListByEmployeeSchduleList
     * @param scheduleIdList 职工日程列表
     * @Description 通过职工日程映射列表，返回职工参与的日程信息
     * @Author Waynejwei
     * @Return java.util.List<com.sicnu.oasystem.pojo.Schedule>
     * @LastChangeDate 2020/11/30
     */
    private List<Map<String,Object>> getScheduleListByEmployeeSchduleList(List<Integer> scheduleIdList){
//        List<Map<String,Object>> resultMap = new ArrayList<>();
//        for (Integer scheduleId : scheduleIdList) {
//            Map<String,Object> map = new HashMap<>(16);
//            map.put("scheduleId",scheduleId);
//            Schedule schedule = requestScheduleData.findScheduleByScheduleId(scheduleId);
//            map.put("startTime",schedule.getStartTime());
//            map.put("endTime",schedule.getEndTime());
//            map.put("content",schedule.getContent());
//            map.put("location",schedule.getLocation());
//            map.put("remark",schedule.getRemark());
//            map.put("type",schedule.getType());
//            map.put("leader", requestEmployeeData.findEmployeeByEmployeeId(schedule.getLeader()).getName());
//            List<Integer> joinerList = requestEmployeeScheduleData.findEmployeeScheduleByScheduleId(scheduleId);
//            log.info("joinerList --> "+joinerList);
//            List<String> joiner = new ArrayList<>();
//            for (Integer employeeId : joinerList) {
//                Employee employee = requestEmployeeData.findEmployeeByEmployeeId(employeeId);
//                joiner.add(employee.getName());
//            }
//            map.put("joiner",joiner);
//            resultMap.add(map);
//        }
//        return resultMap;
        return null;
    }
}
