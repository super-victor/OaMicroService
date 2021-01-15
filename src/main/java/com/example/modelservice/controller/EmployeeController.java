package com.example.modelservice.controller;

import com.example.modelservice.mapper.EmployeeMapper;
import com.example.modelservice.pojo.Employee;
import com.example.modelservice.pojo.Menu;
import com.example.modelservice.pojo.Role;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName EmployeeController
 * @Description 职工控制层
 * @Author Waynejwei
 * @LastChangeDate 2021/1/14 20:04
 * @Version v1.0
 */

@RestController
public class EmployeeController {

    @Resource
    EmployeeMapper employeeMapper;

    @PostMapping("/insertEmployee")
    int insertEmployee(@RequestBody Employee employee){
        return employeeMapper.insertEmployee(employee);
    }

    @DeleteMapping("/deleteEmployee")
    int deleteEmployee(@RequestParam int employeeId){
        return employeeMapper.deleteEmployee(employeeId);
    }

    @GetMapping("/findEmployeeByUsername")
    public Employee findEmployeeByUsername(@RequestParam String username){

        return employeeMapper.findEmployeeByUsername(username);
    }

    @GetMapping("/findEmployeesByDepartmentId")
    List<Employee> findEmployeesByDepartmentId(@RequestParam int departmentId){
        return employeeMapper.findEmployeesByDepartmentId(departmentId);
    }

    @GetMapping("/findEmployeeByEmployeeId")
    Employee findEmployeeByEmployeeId(@RequestParam int employeeId){
        return employeeMapper.findEmployeeByEmployeeId(employeeId);
    }

    @GetMapping("/findRolesByEmployeeId")
    List<Role> findRolesByEmployeeId(@RequestParam int employeeId){
        return employeeMapper.findRolesByEmployeeId(employeeId);
    }

    @GetMapping("/findAvailableRolesByEmployeeId")
    List<Role> findAvailableRolesByEmployeeId(@RequestParam int employeeId){
        return employeeMapper.findAvailableRolesByEmployeeId(employeeId);
    }

    @PutMapping("/updatePartlyEmployeeByEmployeeId")
    int updatePartlyEmployeeByEmployeeId(@RequestParam int employeeId, @RequestParam String phone, @RequestParam String email, @RequestParam String homeAddress){
        return employeeMapper.updatePartlyEmployeeByEmployeeId(employeeId, phone, email, homeAddress);
    }

    @GetMapping("/getAllEmployee")
    List<Employee> getAllEmployee(){
        return employeeMapper.getAllEmployee();
    }

    @GetMapping("/findAllEmployee")
    List<Employee> findAllEmployee(){
        return employeeMapper.findAllEmployee();
    }

    @GetMapping("/getEmployeeBypage")
    List<Employee> getEmployeeBypage(@RequestParam Integer currentPageNum,@RequestParam Integer pageSize){
        return employeeMapper.getEmployeeBypage(currentPageNum, pageSize);
    }

    @GetMapping("/getemployeenumbers")
    Integer getemployeenumbers(){
        return employeeMapper.getemployeenumbers();
    }

    @GetMapping("/getEmployeeByCondition")
    List<Employee>getEmployeeByCondition(@RequestParam String name, @RequestParam String sex){
        return employeeMapper.getEmployeeByCondition(name, sex);
    }

    @GetMapping("/findRoleByEmployeeIdAndRoleId")
    Integer findRoleByEmployeeIdAndRoleId(@RequestParam Integer employeeId, @RequestParam Integer roleId){
        return employeeMapper.findRoleByEmployeeIdAndRoleId(employeeId, roleId);
    }

    @GetMapping("/findEmployeesByRoleId")
    List<Employee> findEmployeesByRoleId(@RequestParam Integer roleId){
        return employeeMapper.findEmployeesByRoleId(roleId);
    }

    @GetMapping("/findRoleIdsByUrlPattern")
    List<String> findRoleIdsByUrlPattern(@RequestParam String urlPattern){
        return employeeMapper.findRoleIdsByUrlPattern(urlPattern);
    }

    @GetMapping("/findCodesByEmployeeId")
    List<String> findCodesByEmployeeId(@RequestParam Integer employeeId){
        return employeeMapper.findCodesByEmployeeId(employeeId);
    }

    @GetMapping("/findCodesByRoleId")
    List<String> findCodesByRoleId(@RequestParam Integer roleId){
        return employeeMapper.findCodesByRoleId(roleId);
    }

    @GetMapping("/findAllMenus")
    List<Menu> findAllMenus(){
        return employeeMapper.findAllMenus();
    }

    @GetMapping("/findAllRoles")
    List<Role> findAllRoles(){
        return employeeMapper.findAllRoles();
    }

    @GetMapping("/findAllRolesExceptA")
    List<Role> findAllRolesExceptA(){
        return employeeMapper.findAllRolesExceptA();
    }

    @GetMapping("/findAllRolesWithEmpolyeeList")
    List<Role> findAllRolesWithEmpolyeeList(){
        return employeeMapper.findAllRolesWithEmpolyeeList();
    }

    @PostMapping("/insertIntoEmployeeRole")
    int insertIntoEmployeeRole(@RequestParam Integer employeeId, @RequestParam Integer roleId){
        return employeeMapper.insertIntoEmployeeRole(employeeId, roleId);
    }

    @DeleteMapping("/deletefromEmployeeRole")
    int deletefromEmployeeRole(@RequestParam Integer employeeId, @RequestParam Integer roleId){
        return employeeMapper.deletefromEmployeeRole(employeeId, roleId);
    }

    @PostMapping("/insertIntoRoleMenu")
    int insertIntoRoleMenu(@RequestParam Integer menuId, @RequestParam Integer roleId){
        return employeeMapper.insertIntoRoleMenu(menuId, roleId);
    }

    @DeleteMapping("/deletefromRoleMenu")
    int deletefromRoleMenu(@RequestParam Integer menuId, @RequestParam Integer roleId){
        return employeeMapper.deletefromRoleMenu(menuId, roleId);
    }

    @DeleteMapping("/deletefromRoleMenuByCode")
    int deletefromRoleMenuByCode(@RequestParam Integer roleId, @RequestParam String code){
        return employeeMapper.deletefromRoleMenuByCode(roleId, code);
    }

    @PostMapping("/insertIntoRoleMenuByCode")
    int insertIntoRoleMenuByCode(@RequestParam Integer roleId, @RequestParam String code){
        return employeeMapper.insertIntoRoleMenuByCode(roleId, code);
    }

    @PutMapping("/updateIsAccountLockedInEmployee")
    int updateIsAccountLockedInEmployee(@RequestParam Integer employeeId, @RequestParam Integer isAccountLocked){
        return employeeMapper.updateIsAccountLockedInEmployee(employeeId, isAccountLocked);
    }

    @GetMapping("/findAllEmployeeExceptA")
    List<Employee> findAllEmployeeExceptA(){
        return employeeMapper.findAllEmployeeExceptA();
    }

    @GetMapping("/findAllAdmin")
    List<Employee> findAllAdmin(){
        return employeeMapper.findAllAdmin();
    }

    @PostMapping("/addAdmin")
    int addAdmin(@RequestParam int employeeId){
        return employeeMapper.addAdmin(employeeId);
    }

    @DeleteMapping("/deleteAdmin")
    int deleteAdmin(@RequestParam int employeeId){
        return employeeMapper.deleteAdmin(employeeId);
    }

    @PostMapping("/insertRole")
    int insertRole(@RequestBody Role role){
        return employeeMapper.insertRole(role);
    }

    @DeleteMapping("/deleteRole")
    int deleteRole(@RequestParam int roleId){
        return employeeMapper.deleteRole(roleId);
    }

    @GetMapping("/findRoleByRoleId")
    Role findRoleByRoleId(@RequestParam int roleId){
        return employeeMapper.findRoleByRoleId(roleId);
    }

    @GetMapping("/findMenusByRoleId")
    List<Menu> findMenusByRoleId(@RequestParam int roleId){
        return employeeMapper.findMenusByRoleId(roleId);
    }

    @GetMapping("/findMenuIsExist")
    int findMenuIsExist(@RequestParam String url){
        return employeeMapper.findMenuIsExist(url);
    }

    @GetMapping("/findTotalEmployeeNum")
    int findTotalEmployeeNum(){
        return employeeMapper.findTotalEmployeeNum();
    }
}
