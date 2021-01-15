package com.sicnu.oasystem.service.datarequest;

import com.sicnu.oasystem.pojo.Equipment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @InterfaceName EquipmentData
 * @Description
 * @Author pan
 * @LastChangeDate 2021/1/15 10:09
 * @Version v1.0
 */
@Service
@FeignClient("MODEL-SERVICE")
public interface EquipmentData {
    @PostMapping("/addEquipment")
    Integer addEquipment(@RequestParam("equipmentclassifyId") Integer equipmentclassifyId,
                         @RequestParam("meetingroomId") Integer meetingroomId,
                         @RequestParam("name") String name,
                         @RequestParam("ismaintain") Integer ismaintain,
                         @RequestParam("remark") String remark,
                         @RequestParam("num") Integer num);


    @PutMapping("/updateEquipment")
    Integer updateEquipment(@RequestParam("equipmentId") Integer equipmentId,
                            @RequestParam("equipmentclassifyId") Integer equipmentclassifyId,
                            @RequestParam("newmeetingroomId") Integer newmeetingroomId,
                            @RequestParam("newname") String newname,
                            @RequestParam("newismaintain") Integer newismaintain,
                            @RequestParam("remark") String remark,
                            @RequestParam("newnum") Integer newnum);

    @DeleteMapping("/deleteEuipment")
    Integer deleteEuipment(@RequestParam("equipmentId") Integer equipmentId);

    @GetMapping("/getEquipmentById")
    Equipment getEquipmentById(@RequestParam("equipmentId") Integer equipmentId);

    @GetMapping("/getallEquipment")
    List<Equipment> getallEquipment();

    @GetMapping("/getEquipmentByclassifyAndRoomAndname")
    Equipment getEquipmentByclassifyAndRoomAndname(@RequestParam("equipmentclassifyId") Integer equipmentclassifyId,
                                                   @RequestParam("meetingroomId") Integer meetingroomId,
                                                   @RequestParam("name") String name);

    @GetMapping("/getEquipmentByCondition")
    List<Equipment> getEquipmentByCondition(@RequestParam("meetingroomid") Integer meetingroomid,
                                            @RequestParam("equipmentclassifyId") Integer equipmentclassifyId,
                                            @RequestParam("name") String name);
}
