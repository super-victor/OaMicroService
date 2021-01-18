package com.example.scheduleservice.request;

import com.example.scheduleservice.pojo.Employee;
import com.example.scheduleservice.pojo.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

    /**
     * @MethodName findEmployeeByEmployeeId
     * @param employeeId 用户Id
     * @Description 通过用户Id获取用户信息
     * @Author Waynejwei
     * @Return com.example.scheduleservice.pojo.Employee
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findEmployeeByEmployeeId")
    Employee findEmployeeByEmployeeId(@RequestParam int employeeId);

    /**
     * @MethodName findRoleByEmployeeIdAndRoleId
     * @param employeeId 用户id
     * @param roleId 角色id
     * @Description 获取用户角色
     * @Author Waynejwei
     * @Return java.lang.Integer
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findRoleByEmployeeIdAndRoleId")
    Integer findRoleByEmployeeIdAndRoleId(@RequestParam Integer employeeId, @RequestParam Integer roleId);

    /**
     * @MethodName findEmployeeByUsername
     * @param username 用户名
     * @Description 通过用户名获取用户信息
     * @Author Waynejwei
     * @Return com.example.scheduleservice.pojo.Employee
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findEmployeeByUsername")
    Employee findEmployeeByUsername(@RequestParam String username);

    /**
     * @MethodName findAvailableRolesByEmployeeId
     * @param employeeId 用户Id
     * @Description 获取用户是否有某一权限
     * @Author Waynejwei
     * @Return java.util.List<com.example.scheduleservice.pojo.Role>
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findAvailableRolesByEmployeeId")
    List<Role> findAvailableRolesByEmployeeId(@RequestParam int employeeId);

    /**
     * @MethodName findCodesByEmployeeId
     * @param employeeId 用户id
     * @Description 查找用户权限码
     * @Author Waynejwei
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findCodesByEmployeeId")
    List<String> findCodesByEmployeeId(@RequestParam Integer employeeId);

    /**
     * @MethodName findMenuIsExist
     * @param url url
     * @Description 查找功能是否存在
     * @Author Waynejwei
     * @Return int
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findMenuIsExist")
    int findMenuIsExist(@RequestParam String url);

    /**
     * @MethodName findRoleIdsByUrlPattern
     * @param urlPattern url
     * @Description 查找url所需要的角色
     * @Author Waynejwei
     * @Return java.util.List<java.lang.String>
     * @LastChangeDate 2021/1/18
     */
    @GetMapping("/findRoleIdsByUrlPattern")
    List<String> findRoleIdsByUrlPattern(@RequestParam String urlPattern);
}