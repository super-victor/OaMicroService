package com.example.scheduleservice.service;


import com.example.scheduleservice.json.BackFrontMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName MessageServiceImpl
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2020/11/25 22:03
 * @Version v1.0
 */

@Service
public class MessageServiceImpl implements MessageService{

//    @Resource
//    RequestMessageData requestMessageData;

    @Override
    public int send(int employeeId, int type, String title, String content) {
//        Message message = new Message();
//        message.setEmployeeId(employeeId);
//        message.setTitle(title);
//        message.setType(type);
//        message.setContent(content);
//        return requestMessageData.insertMessage(message);
        return 0;
    }

    @Override
    public BackFrontMessage getSelfAllMessage() {

//        return new BackFrontMessage(200,"获取成功", requestMessageData.findMessageByEmployeeId(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId()));
        return null;
    }

    @Override
    public BackFrontMessage getSelfAllUnReadMessage() {
//        return new BackFrontMessage(200,"获取成功", requestMessageData.findMessageByEmployeeIdAndIsRead(UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),0));
        return null;
    }

    @Override
    public BackFrontMessage setSelfMessageRead(int messageId) {
//        if (requestMessageData.updateIsReadInMessage(messageId,UserAuthenticationUtils.getCurrentUserFromSecurityContext().getEmployeeId(),1) != 0) {
//            return new BackFrontMessage(200,"修改成功",null);
//        } else {
//            return new BackFrontMessage(500,"修改失败",null);
//        }
        return null;
    }
}
