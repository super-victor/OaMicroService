package com.oa.provider;

import com.oa.service.message.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName MessageServiceProvider
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/15 10:35
 * @Version v1.0
 */

@RestController
public class MessageServiceProvider {

    @Resource
    MessageService messageService;

    @PostMapping("/message/send")
    public int send(@RequestParam int employeeId, @RequestParam int type,@RequestParam String title,@RequestParam String content) {
        return messageService.send(employeeId, type, title, content);
    }
}
