package com.example.modelservice.controller;

import com.example.modelservice.mapper.DepartmentMapper;
import org.springframework.web.bind.annotation.GetMapping;
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
    DepartmentMapper departmentMapper;


    @GetMapping("/getAllDepartmentName")
    List<String> getAllDepartmentName(){
        return departmentMapper.getAllDepartmentName();
    }
}

