package com.oa.consume;

import com.oa.pojo.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @InterfaceName MessageMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/14 19:47
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface MessageMapper {

    @PostMapping("/insertMessage")
    int insertMessage(@RequestBody Message message);

    @GetMapping("/findMessageByEmployeeId")
    List<Message> findMessageByEmployeeId(@RequestParam int employeeId);

    @GetMapping("/findMessageByEmployeeIdAndIsRead")
    List<Message> findMessageByEmployeeIdAndIsRead(@RequestParam int employeeId,@RequestParam int isRead);

    @PutMapping("/updateIsReadInMessage")
    int updateIsReadInMessage(@RequestParam int messageId,@RequestParam int employeeId,@RequestParam int isRead);
}
