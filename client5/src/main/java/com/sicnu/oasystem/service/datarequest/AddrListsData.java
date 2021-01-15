package com.sicnu.oasystem.service.datarequest;

import com.sicnu.oasystem.pojo.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.GET;
import java.util.List;

/**
 * @InterfaceName addrlistsdata
 * @Description
 * @Author pan
 * @LastChangeDate 2021/1/14 16:20
 * @Version v1.0
 */
@FeignClient("MODEL-SERVICE")
@Service
public interface AddrListsData {
    @GetMapping("/getAllEmployee")
    List<Employee>getAllEmployee();


    @GetMapping("/getAllDepartmentName")
    List<String>getAllDepartmentName();

    @GetMapping("/getemployeenumbers")
    int getemployeenumbers();

    @GetMapping("/getEmployeeBypage")
    List<Employee>getEmployeeBypage(@RequestParam("currentPageNum") Integer currentPageNum,@RequestParam("pageSize") Integer pageSize);

    @GetMapping("/getEmployeeByCondition")
    List<Employee>getEmployeeByCondition(@RequestParam("name") String name, @RequestParam("sex") String sex);
}
