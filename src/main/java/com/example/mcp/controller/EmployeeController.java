package com.example.mcp.controller;

import com.example.mcp.entity.Employee;
import com.example.mcp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 员工岗位查询 Controller
 *
 * 提供根据人员姓名查询工作岗位的 API
 */
@Slf4j
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 根据姓名查询岗位
     */
    @GetMapping("/position")
    public Map<String, Object> getJobPosition(@RequestParam String name) {
        log.info("查询人员岗位: {}", name);
        Employee employee = employeeService.getEmployeeByName(name);
        if (employee != null) {
            return Map.of("name", employee.getName(), "position", employee.getPosition());
        }
        return Map.of("name", name, "position", "未找到该人员信息");
    }

    /**
     * 获取所有员工
     */
    @GetMapping("/list")
    public List<Employee> getAllEmployees() {
        log.info("获取所有员工");
        return employeeService.getAllEmployees();
    }

    /**
     * 创建员工
     */
    @PostMapping
    public Employee createEmployee(@RequestBody Map<String, String> employee) {
        log.info("创建员工: {}", employee);
        Employee newEmployee = new Employee();
        newEmployee.setName(employee.get("name"));
        newEmployee.setPosition(employee.get("position"));
        return employeeService.createEmployee(newEmployee);
    }

    /**
     * 更新员工岗位
     */
    @PatchMapping("/position")
    public Map<String, Object> updateEmployeePosition(@RequestBody Map<String, String> requestBody) {
        log.info("更新员工岗位: {}", requestBody);
        String name = requestBody.get("name");
        String position = requestBody.get("position");
        boolean updated = employeeService.updateEmployeePosition(name, position);
        return Map.of("name", name, "position", position, "updated", updated);
    }

    /**
     * 删除员工
     */
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteEmployee(@PathVariable Integer id) {
        log.info("删除员工: {}", id);
        boolean deleted = employeeService.deleteEmployee(id);
        return Map.of("id", id, "deleted", deleted);
    }
}
