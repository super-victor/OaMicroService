package com.oa;

import com.oa.consume.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AdminApplicationTests {
    @Resource
    EmployeeMapper employeeMapper;

    @Test
    void contextLoads() {
        System.out.println(employeeMapper.findAllEmployee());


    }

}
