package com.example.scheduleservice.request;

import com.example.scheduleservice.pojo.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @InterfaceName RequestCardData
 * @Description 添加描述
 * @Author Waynejwei
 * @LastChangeDate 2021/1/16 15:49
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface RequestCardData {

    /**
     * @MethodName findCardByEmployeeId
     * @param employeeId 用户ID
     * @Description 获取一个人的名片
     * @Author Waynejwei
     * @Return java.util.List<com.example.scheduleservice.pojo.Card>
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findCardByEmployeeId")
    List<Card> findCardByEmployeeId(@RequestParam int employeeId);
}