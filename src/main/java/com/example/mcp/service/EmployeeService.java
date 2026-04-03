package com.example.mcp.service;

import com.example.mcp.entity.Employee;

import java.util.List;

/**
 * 员工服务接口
 */
public interface EmployeeService {

    /**
     * 查询所有员工
     */
    List<Employee> getAllEmployees();

    /**
     * 根据姓名查询员工岗位
     */
    Employee getEmployeeByName(String name);

    /**
     * 创建员工
     */
    Employee createEmployee(Employee employee);

    /**
     * 更新员工岗位
     */
    boolean updateEmployeePosition(String name, String position);

    /**
     * 删除员工
     */
    boolean deleteEmployee(Integer id);
}
