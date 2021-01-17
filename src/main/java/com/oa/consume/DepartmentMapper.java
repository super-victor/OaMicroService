package com.oa.consume;

import com.oa.pojo.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * @InterfaceName DepartmentMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/15 9:58
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface DepartmentMapper {

    @GetMapping("/findAllDepartment")
    List<Department> findAllDepartment();
}
