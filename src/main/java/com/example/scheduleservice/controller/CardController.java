package com.example.scheduleservice.controller;

import com.example.scheduleservice.json.BackFrontMessage;
import com.example.scheduleservice.service.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName CardController
 * @Description 名片控制类
 * @Author Waynejwei
 * @LastChangeDate 2021/1/16 15:53
 * @Version v1.0
 */

@RestController
public class CardController {

    @Resource
    CardService cardService;

    /**
     * @MethodName findCardByEmployeeId
     * @Description 获取用户的所有名片
     * @Author Waynejwei
     * @Return com.example.scheduleservice.json.BackFrontMessage
     * @LastChangeDate 2021/1/16
     */
    @GetMapping("/Card")
    public BackFrontMessage findCardByEmployeeId(){
        return cardService.findCardByEmployeeId();
    }
}
