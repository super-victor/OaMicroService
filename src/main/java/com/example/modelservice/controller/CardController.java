package com.example.modelservice.controller;

import com.example.modelservice.mapper.CardHolderMapper;
import com.example.modelservice.mapper.CardMapper;
import com.example.modelservice.pojo.Card;
import com.example.modelservice.pojo.CardHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName cardController
 * @Description 名片夹控制层
 * @Author Waynejwei
 * @LastChangeDate 2021/1/14 9:18
 * @Version v1.0
 */

@RestController
@Slf4j
public class CardController {

    @Resource
    CardHolderMapper cardHolderMapper;

    @Resource
    CardMapper cardMapper;

    @GetMapping("/findCardHolderByEmployeeId")
    public List<CardHolder> findCardHolderByEmployeeId(Integer employeeId){
        List<CardHolder> cardHolderList = cardHolderMapper.findCardHolderByEmployeeId(employeeId);
        System.out.println(cardHolderList);
        return cardHolderList;
    }

    @PostMapping("/CardHolder")
    public int insertCardHolder(@RequestParam CardHolder cardHolder){
        int result = cardHolderMapper.insertCardHolderByCardHolderId(cardHolder);
        if (result == 1){
            log.info("添加名片夹成功");
        } else {
            log.info("添加名片夹失败");
        }
        return result;
    }

    @GetMapping("/CardHolderByName")
    public CardHolder findCardHolderByName(@RequestParam String name, @RequestParam int employeeId){
        return cardHolderMapper.findCardHolderByName(name, employeeId);
    }

    @GetMapping("/CardHolderByCardHolderId")
    public CardHolder findCardHolderByCardHolderId(@RequestParam int cardHolderId){
        return cardHolderMapper.findCardHolderByCardHolderId(cardHolderId);
    }

    @GetMapping("/CardHolderByCardHolderIdAndEmployeeId")
    public CardHolder findCardHolderByCardHolderIdAndEmployeeId(@RequestParam int cardHolderId, @RequestParam int employeeId){
        return cardHolderMapper.findCardHolderByCardHolderIdAndEmployeeId(cardHolderId, employeeId);
    }

    @DeleteMapping("/CardHolder")
    public int deleteCardHolder(@RequestParam int cardHolderId){
        return cardHolderMapper.deleteCardHolderByCardHolderId(cardHolderId);
    }

    @PutMapping("/CardHolder")
    public int updateCardHolder(@RequestParam int cardHolderId, @RequestParam String name){
        return cardHolderMapper.updateCardHolderNameByCardHolderId(cardHolderId, name);
    }

    @GetMapping("/findCardByEmployeeId")
    public List<Card> findCardByEmployeeId(@RequestParam int employeeId){
        return cardMapper.findCardByEmployeeId(employeeId);
    }
}
