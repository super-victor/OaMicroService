package com.example.modelservice.controller;

import com.example.modelservice.mapper.EquipmentClassifyMapper;
import com.example.modelservice.pojo.EquipmentClassify;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import java.util.List;

/**
 * @ClassName equipmentClassifyController
 * @Description 设备分类控制器
 * @Author pan
 * @LastChangeDate 2021/1/14 18:53
 * @Version v1.0
 */

@RestController
public class EquipmentClassifyController {
    @Resource
    EquipmentClassifyMapper equipmentClassifyMapper;

    @GetMapping("/EquipmentClassifyIfExitByName")
    int EquipmentClassifyIfExitByName(@RequestParam String name){
        return equipmentClassifyMapper.EquipmentClassifyIfExitByName(name);
    }

    @PostMapping("/addEquipmentClassify")
    int addEquipmentClassify(@RequestParam String classifyName){
        return equipmentClassifyMapper.addEquipmentClassify(classifyName);
    }

    @GetMapping("/getEquipmentClassifyById")
    EquipmentClassify getEquipmentClassifyById(Integer classifyId){
        return equipmentClassifyMapper.getEquipmentClassifyById(classifyId);
    }

    @PostMapping("/updateEquipmentClassify")
    int updateEquipmentClassify(@RequestParam Integer classifyId, @RequestParam String newClassifyName){
        return equipmentClassifyMapper.updateEquipmentClassify(classifyId,newClassifyName);
    }

    @DeleteMapping("/deleteEquipmentClassify")
    int deleteEquipmentClassify(@RequestParam Integer ClassifyId){
        return equipmentClassifyMapper.deleteEquipmentClassify(ClassifyId);
    }

    @GetMapping("/getAllEquipmentClassify")
    List<EquipmentClassify> getAllEquipmentClassify(){
        return equipmentClassifyMapper.getAllEquipmentClassify();
    }
}

