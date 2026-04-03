package com.example.mcp.mapper;

import com.example.mcp.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工 Mapper 接口
 */
@Mapper
public interface EmployeeMapper {

    /**
     * 查询所有员工
     */
    List<Employee> selectAll();

    /**
     * 根据姓名查询员工
     */
    Employee selectByName(@Param("name") String name);

    /**
     * 插入员工
     */
    int insert(Employee employee);

    /**
     * 更新员工岗位
     */
    int updatePosition(@Param("name") String name, @Param("position") String position);

    /**
     * 删除员工
     */
    int deleteById(@Param("id") Integer id);
}
