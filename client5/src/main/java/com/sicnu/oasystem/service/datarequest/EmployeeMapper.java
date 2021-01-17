package com.sicnu.oasystem.service.datarequest;

import com.sicnu.oasystem.pojo.Employee;
import com.sicnu.oasystem.pojo.Role;
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
    int deleteEmployee(@RequestParam("employeeId") int employeeId);

    @GetMapping("/findEmployeeByUsername")
    Employee findEmployeeByUsername(@RequestParam("username") String username);

    @GetMapping("/findEmployeesByDepartmentId")
    List<Employee> findEmployeesByDepartmentId(@RequestParam("departmentId") int departmentId);

    @GetMapping("/findEmployeeByEmployeeId")
    Employee findEmployeeByEmployeeId(@RequestParam("employeeId") int employeeId);

    @GetMapping("/findRolesByEmployeeId")
    List<Role> findRolesByEmployeeId(@RequestParam("employeeId") int employeeId);

    @GetMapping("/findAvailableRolesByEmployeeId")
    List<Role> findAvailableRolesByEmployeeId(@RequestParam("employeeId") int employeeId);

    @PutMapping("/updatePartlyEmployeeByEmployeeId")
    int updatePartlyEmployeeByEmployeeId(@RequestParam("employeeId") int employeeId,
                                         @RequestParam("phone") String phone,
                                         @RequestParam("email") String email,
                                         @RequestParam("homeAddress") String homeAddress);

    @GetMapping("/getAllEmployee")
    List<Employee> getAllEmployee();
    @GetMapping("/findAllEmployee")
    List<Employee> findAllEmployee();

    @GetMapping("/getEmployeeBypage")
    List<Employee> getEmployeeBypage(@RequestParam("currentPageNum") Integer currentPageNum,
                                     @RequestParam("pageSize") Integer pageSize);

    @GetMapping("/getemployeenumbers")
    Integer getemployeenumbers();

    @GetMapping("/getEmployeeByCondition")
    List<Employee>getEmployeeByCondition(@RequestParam("name") String name,
                                         @RequestParam("sex") String sex);

    @GetMapping("/findRoleByEmployeeIdAndRoleId")
    Integer findRoleByEmployeeIdAndRoleId(@RequestParam("employeeId") Integer employeeId,
                                          @RequestParam("roleId") Integer roleId);

    @GetMapping("/findEmployeesByRoleId")
    List<Employee> findEmployeesByRoleId(@RequestParam("roleId") Integer roleId);

    @GetMapping("/findRoleIdsByUrlPattern")
    List<String> findRoleIdsByUrlPattern(@RequestParam("urlPattern") String urlPattern);

    @GetMapping("/findCodesByEmployeeId")
    List<String> findCodesByEmployeeId(@RequestParam("employeeId") Integer employeeId);

    @GetMapping("/findCodesByRoleId")
    List<String> findCodesByRoleId(@RequestParam("roleId") Integer roleId);

    @GetMapping("/findAllMenus")
    List<Menu> findAllMenus();

    @GetMapping("/findAllRoles")
    List<Role> findAllRoles();

    @GetMapping("/findAllRolesExceptA")
    List<Role> findAllRolesExceptA();

    @GetMapping("/findAllRolesWithEmpolyeeList")
    List<Role> findAllRolesWithEmpolyeeList();

    @PostMapping("/insertIntoEmployeeRole")
    int insertIntoEmployeeRole(@RequestParam("employeeId") Integer employeeId,
                               @RequestParam("roleId") Integer roleId);

    @DeleteMapping("/deletefromEmployeeRole")
    int deletefromEmployeeRole(@RequestParam("employeeId") Integer employeeId,
                               @RequestParam("roleId") Integer roleId);

    @PostMapping("/insertIntoRoleMenu")
    int insertIntoRoleMenu(@RequestParam("menuId") Integer menuId,
                           @RequestParam("roleId") Integer roleId);

    @DeleteMapping("/deletefromRoleMenu")
    int deletefromRoleMenu(@RequestParam("menuId") Integer menuId,
                           @RequestParam("roleId") Integer roleId);

    @DeleteMapping("/deletefromRoleMenuByCode")
    int deletefromRoleMenuByCode(@RequestParam("roleId") Integer roleId,
                                 @RequestParam("code") String code);

    @PostMapping("/insertIntoRoleMenuByCode")
    int insertIntoRoleMenuByCode(@RequestParam("roleId") Integer roleId,
                                 @RequestParam("code") String code);

    @PutMapping("/updateIsAccountLockedInEmployee")
    int updateIsAccountLockedInEmployee(@RequestParam("employeeId") Integer employeeId,
                                        @RequestParam("isAccountLocked") Integer isAccountLocked);

    @GetMapping("/findAllEmployeeExceptA")
    List<Employee> findAllEmployeeExceptA();

    @GetMapping("/findAllAdmin")
    List<Employee> findAllAdmin();

    @PostMapping("/addAdmin")
    int addAdmin(@RequestParam("employeeId") int employeeId);

    @DeleteMapping("/deleteAdmin")
    int deleteAdmin(@RequestParam("employeeId") int employeeId);

    @PostMapping("/insertRole")
    int insertRole(@RequestBody Role role);

    @DeleteMapping("/deleteRole")
    int deleteRole(@RequestParam("roleId") int roleId);

    @GetMapping("/findRoleByRoleId")
    Role findRoleByRoleId(@RequestParam("roleId") int roleId);

    @GetMapping("/findMenusByRoleId")
    List<Menu> findMenusByRoleId(@RequestParam("roleId") int roleId);

    @GetMapping("/findMenuIsExist")
    int findMenuIsExist(@RequestParam("url") String url);

    @GetMapping("/findTotalEmployeeNum")
    int findTotalEmployeeNum();
}
