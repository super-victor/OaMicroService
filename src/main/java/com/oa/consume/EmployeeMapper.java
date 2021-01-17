package com.oa.consume;

import com.oa.pojo.Employee;
import com.oa.pojo.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

/**
 * @InterfaceName EmployeeMapper
 * @Description 添加描述
 * @Author JohnTang
 * @LastChangeDate 2021/1/15 9:26
 * @Version v1.0
 */

@FeignClient(name = "model-service")
@Service
public interface EmployeeMapper {

    @PostMapping("/insertEmployee")
    int insertEmployee(@RequestBody Employee employee);

    @DeleteMapping("/deleteEmployee")
    int deleteEmployee(@RequestParam int employeeId);

    @GetMapping("/findEmployeeByUsername")
    Employee findEmployeeByUsername(@RequestParam String username);

    @GetMapping("/findEmployeesByDepartmentId")
    List<Employee> findEmployeesByDepartmentId(@RequestParam int departmentId);

    @GetMapping("/findEmployeeByEmployeeId")
    Employee findEmployeeByEmployeeId(@RequestParam int employeeId);

    @GetMapping("/findRolesByEmployeeId")
    List<Role> findRolesByEmployeeId(@RequestParam int employeeId);

    @GetMapping("/findAvailableRolesByEmployeeId")
    List<Role> findAvailableRolesByEmployeeId(@RequestParam int employeeId);

    @PutMapping("/updatePartlyEmployeeByEmployeeId")
    int updatePartlyEmployeeByEmployeeId(@RequestParam int employeeId, @RequestParam String phone, @RequestParam String email, @RequestParam String homeAddress);

    @GetMapping("/getAllEmployee")
    List<Employee> getAllEmployee();
    @GetMapping("/findAllEmployee")
    List<Employee> findAllEmployee();

    @GetMapping("/getEmployeeBypage")
    List<Employee> getEmployeeBypage(@RequestParam Integer currentPageNum,@RequestParam Integer pageSize);

    @GetMapping("/getemployeenumbers")
    Integer getemployeenumbers();

    @GetMapping("/getEmployeeByCondition")
    List<Employee>getEmployeeByCondition(@RequestParam String name, @RequestParam String sex);

    @GetMapping("/findRoleByEmployeeIdAndRoleId")
    Integer findRoleByEmployeeIdAndRoleId(@RequestParam Integer employeeId, @RequestParam Integer roleId);

    @GetMapping("/findEmployeesByRoleId")
    List<Employee> findEmployeesByRoleId(@RequestParam Integer roleId);

    @GetMapping("/findRoleIdsByUrlPattern")
    List<String> findRoleIdsByUrlPattern(@RequestParam String urlPattern);

    @GetMapping("/findCodesByEmployeeId")
    List<String> findCodesByEmployeeId(@RequestParam Integer employeeId);

    @GetMapping("/findCodesByRoleId")
    List<String> findCodesByRoleId(@RequestParam Integer roleId);

    @GetMapping("/findAllMenus")
    List<Menu> findAllMenus();

    @GetMapping("/findAllRoles")
    List<Role> findAllRoles();

    @GetMapping("/findAllRolesExceptA")
    List<Role> findAllRolesExceptA();

    @GetMapping("/findAllRolesWithEmpolyeeList")
    List<Role> findAllRolesWithEmpolyeeList();

    @PostMapping("/insertIntoEmployeeRole")
    int insertIntoEmployeeRole(@RequestParam Integer employeeId, @RequestParam Integer roleId);

    @DeleteMapping("/deletefromEmployeeRole")
    int deletefromEmployeeRole(@RequestParam Integer employeeId, @RequestParam Integer roleId);

    @PostMapping("/insertIntoRoleMenu")
    int insertIntoRoleMenu(@RequestParam Integer menuId, @RequestParam Integer roleId);

    @DeleteMapping("/deletefromRoleMenu")
    int deletefromRoleMenu(@RequestParam Integer menuId, @RequestParam Integer roleId);

    @DeleteMapping("/deletefromRoleMenuByCode")
    int deletefromRoleMenuByCode(@RequestParam Integer roleId, @RequestParam String code);

    @PostMapping("/insertIntoRoleMenuByCode")
    int insertIntoRoleMenuByCode(@RequestParam Integer roleId, @RequestParam String code);

    @PutMapping("/updateIsAccountLockedInEmployee")
    int updateIsAccountLockedInEmployee(@RequestParam Integer employeeId, @RequestParam Integer isAccountLocked);

    @GetMapping("/findAllEmployeeExceptA")
    List<Employee> findAllEmployeeExceptA();

    @GetMapping("/findAllAdmin")
    List<Employee> findAllAdmin();

    @PostMapping("/addAdmin")
    int addAdmin(@RequestParam int employeeId);

    @DeleteMapping("/deleteAdmin")
    int deleteAdmin(@RequestParam int employeeId);

    @PostMapping("/insertRole")
    int insertRole(@RequestBody Role role);

    @DeleteMapping("/deleteRole")
    int deleteRole(@RequestParam int roleId);

    @GetMapping("/findRoleByRoleId")
    Role findRoleByRoleId(@RequestParam int roleId);

    @GetMapping("/findMenusByRoleId")
    List<Menu> findMenusByRoleId(@RequestParam int roleId);

    @GetMapping("/findMenuIsExist")
    int findMenuIsExist(@RequestParam String url);

    @GetMapping("/findTotalEmployeeNum")
    int findTotalEmployeeNum();
}
