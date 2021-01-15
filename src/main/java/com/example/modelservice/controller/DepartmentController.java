package com.example.modelservice.controller;

import com.example.modelservice.mapper.DepartmentMapper;
import com.example.modelservice.pojo.Department;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName DepartmentController
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/15 9:56
 * @Version v1.0
 */

@RestController
public class DepartmentController {

    @Resource
    DepartmentMapper departmentMapper;

    @GetMapping("/findAllDepartment")
    public List<Department> findAllDepartment(){
        return departmentMapper.findAllDepartment();
    }
}
