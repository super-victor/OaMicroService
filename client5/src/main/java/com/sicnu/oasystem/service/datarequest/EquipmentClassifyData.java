package com.sicnu.oasystem.service.datarequest;

import com.sicnu.oasystem.pojo.EquipmentClassify;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @InterfaceName EquipmentClassifyData
 * @Description
 * @Author pan
 * @LastChangeDate 2021/1/15 10:01
 * @Version v1.0
 */
@Service
@FeignClient("MODEL-SERVICE")
public interface EquipmentClassifyData {

    @GetMapping("/EquipmentClassifyIfExitByName")
    int EquipmentClassifyIfExitByName(@RequestParam("name") String name);

    @PostMapping("/addEquipmentClassify")
    int addEquipmentClassify(@RequestParam("classifyName") String classifyName);

    @GetMapping("/getEquipmentClassifyById")
    EquipmentClassify getEquipmentClassifyById(@RequestParam("classifyId") Integer classifyId);

    @PostMapping("/updateEquipmentClassify")
    int updateEquipmentClassify(@RequestParam("classifyId") Integer classifyId,
                                @RequestParam("newClassifyName") String newClassifyName);

    @DeleteMapping("/deleteEquipmentClassify")
    int deleteEquipmentClassify(@RequestParam("ClassifyId") Integer ClassifyId);

    @GetMapping("/getAllEquipmentClassify")
    List<EquipmentClassify> getAllEquipmentClassify();

}
