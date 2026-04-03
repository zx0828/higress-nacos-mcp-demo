package com.example.mcp.service.impl;

import com.example.mcp.entity.Employee;
import com.example.mcp.mapper.EmployeeMapper;
import com.example.mcp.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工服务实现
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.selectAll();
    }

    @Override
    public Employee getEmployeeByName(String name) {
        return employeeMapper.selectByName(name);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        employee.setCreateTime(LocalDateTime.now());
        employeeMapper.insert(employee);
        return employee;
    }

    @Override
    public boolean updateEmployeePosition(String name, String position) {
        return employeeMapper.updatePosition(name, position) > 0;
    }

    @Override
    public boolean deleteEmployee(Integer id) {
        return employeeMapper.deleteById(id) > 0;
    }
}
