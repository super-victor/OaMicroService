package com.oa.wx.dao;

import com.oa.wx.pojo.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName EmployeeMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/16 10:06
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface EmployeeMapper {

    @GetMapping("/findEmployeeByEmployeeId")
    Employee findEmployeeByEmployeeId(@RequestParam int employeeId);

    @GetMapping("/findEmployeeByUsername")
    Employee findEmployeeByUsername(@RequestParam String username);
}
