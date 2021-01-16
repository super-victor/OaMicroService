package com.example.scheduleservice.service;

import com.example.scheduleservice.json.BackFrontMessage;
import com.example.scheduleservice.pojo.Employee;
import com.example.scheduleservice.request.RequestCardData;
import com.example.scheduleservice.utils.UserAuthenticationUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName CardServiceImpl
 * @Description 名片服务实现类
 * @Author Waynejwei
 * @LastChangeDate 2021/1/16 15:50
 * @Version v1.0
 */

@Service
public class CardServiceImpl implements CardService{

    @Resource
    RequestCardData requestCardData;

    @Override
    public BackFrontMessage findCardByEmployeeId() {
        Employee currentEmployee = UserAuthenticationUtils.getCurrentUserFromSecurityContext();
        return new BackFrontMessage(200, "查找成功", requestCardData.findCardByEmployeeId(currentEmployee.getEmployeeId()));
    }
}
