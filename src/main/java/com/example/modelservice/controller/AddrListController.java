package com.example.modelservice.controller;

import com.example.modelservice.mapper.DepartmentMapper;
import com.example.modelservice.mapper.EmployeeMapper;
import com.example.modelservice.pojo.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName addrlistcontroller
 * @Description 通讯录控制器
 * @Author pan
 * @LastChangeDate 2021/1/14 18:41
 * @Version v1.0
 */

@RestController
public class AddrListController {

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    DepartmentMapper departmentMapper;

    @GetMapping("/getAllEmployee")
    List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }

    @GetMapping("/getAllDepartmentName")
    List<String> getAllDepartmentName(){
        return departmentMapper.getAllDepartmentName();
    }

    @GetMapping("/getemployeenumbers")
    int getemployeenumbers(){
        return employeeMapper.getemployeenumbers();
    }

    @GetMapping("/getEmployeeBypage")
    List<Employee>getEmployeeBypage(@RequestParam Integer currentPageNum, @RequestParam Integer pageSize){
        return employeeMapper.getEmployeeBypage(currentPageNum,pageSize);
    }

    @GetMapping("/getEmployeeByCondition")
    List<Employee>getEmployeeByCondition(@RequestParam String name, @RequestParam String sex){
        return employeeMapper.getEmployeeByCondition(name,sex);
    }
}

