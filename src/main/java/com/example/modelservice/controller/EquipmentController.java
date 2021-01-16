package com.example.modelservice.controller;

import com.example.modelservice.mapper.EquipmentMapper;
import com.example.modelservice.pojo.Equipment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName equipmentController
 * @Description 设备控制器
 * @Author pan
 * @LastChangeDate 2021/1/14 19:05
 * @Version v1.0
 */
@RestController
public class EquipmentController {

    @Resource
    EquipmentMapper equipmentMapper;


    @PostMapping("/addEquipment")
    Integer addEquipment(@RequestParam Integer equipmentclassifyId,
                         @RequestParam Integer meetingroomId,
                         @RequestParam String name,
                         @RequestParam Integer ismaintain,
                         @RequestParam String remark,
                         @RequestParam Integer num){
        return equipmentMapper.addEquipment(equipmentclassifyId,meetingroomId,name,ismaintain,remark,num);
    }

    @GetMapping("/getEquipmentById")
    Equipment getEquipmentById(Integer equipmentId){
        return equipmentMapper.getEquipmentById(equipmentId);
    }

    @PutMapping("/updateEquipment")
    Integer updateEquipment(@RequestParam Integer equipmentId,
                            @RequestParam(required = false) Integer equipmentclassifyId,
                            @RequestParam(required = false) Integer newmeetingroomId,
                            @RequestParam(required = false) String newname,
                            @RequestParam(required = false) Integer newismaintain,
                            @RequestParam(required = false) String remark,
                            @RequestParam(required = false) Integer newnum){
        return equipmentMapper.updateEquipment(equipmentId,equipmentclassifyId,newmeetingroomId,newname,newismaintain,remark,newnum);
    }

    @DeleteMapping("/deleteEuipment")
    Integer deleteEuipment(@RequestParam Integer equipmentId){
        return equipmentMapper.deleteEuipment(equipmentId);
    }

    @GetMapping("/getallEquipment")
    List<Equipment> getallEquipment(){
        return equipmentMapper.getallEquipment();
    }

    @GetMapping("/getEquipmentByclassifyAndRoomAndname")
    Equipment getEquipmentByclassifyAndRoomAndname(@RequestParam Integer equipmentclassifyId,
                                                   @RequestParam Integer meetingroomId,
                                                   @RequestParam String name){
        return equipmentMapper.getEquipmentByclassifyAndRoomAndname(equipmentclassifyId,meetingroomId,name);
    }

    @GetMapping("/getEquipmentByCondition")
    List<Equipment> getEquipmentByCondition(@RequestParam(required = false) Integer meetingroomid,
                                            @RequestParam(required = false) Integer equipmentclassifyId,
                                            @RequestParam(required = false) String name){
        return equipmentMapper.getEquipmentByCondition(meetingroomid,equipmentclassifyId,name);
    }
}

