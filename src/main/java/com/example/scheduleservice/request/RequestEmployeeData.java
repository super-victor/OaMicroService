package com.example.scheduleservice.request;

import com.example.scheduleservice.pojo.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName RequestEmployeeData
 * @Description 添加描述
 * @Author Waynejwei
 * @LastChangeDate 2021/1/15 8:39
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface RequestEmployeeData {

    @GetMapping("/findEmployeeByEmployeeId")
    Employee findEmployeeByEmployeeId(@RequestParam int employeeId);

    @GetMapping("/findRoleByEmployeeIdAndRoleId")
    Integer findRoleByEmployeeIdAndRoleId(@RequestParam Integer employeeId, @RequestParam Integer roleId);
}