package com.example.modelservice.controller;

import com.example.modelservice.mapper.MessageMapper;
import com.example.modelservice.pojo.Message;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MessageContoller
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/14 19:26
 * @Version v1.0
 */
@RestController
public class MessageController {

    @Resource
    MessageMapper messageMapper;

    @PostMapping("/insertMessage")
    public int insertMessage(@RequestBody Message message){
        return messageMapper.insertMessage(message);
    }

    @GetMapping("/findMessageByEmployeeId")
    public List<Message> findMessageByEmployeeId(@RequestParam int employeeId){
        return messageMapper.findMessageByEmployeeId(employeeId);
    }

    @GetMapping("/findMessageByEmployeeIdAndIsRead")
    public List<Message> findMessageByEmployeeIdAndIsRead(@RequestParam int employeeId,@RequestParam int isRead){
        return messageMapper.findMessageByEmployeeIdAndIsRead(employeeId,isRead);
    }

    @PutMapping("/updateIsReadInMessage")
    public int updateIsReadInMessage(@RequestParam int messageId,@RequestParam int employeeId,@RequestParam int isRead){
        return messageMapper.updateIsReadInMessage(messageId, employeeId,isRead);
    }
}
