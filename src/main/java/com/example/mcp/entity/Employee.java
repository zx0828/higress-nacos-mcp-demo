package com.example.mcp.entity;

import java.time.LocalDateTime;

/**
 * 员工实体类
 */
public class Employee {
    /** 主键ID */
    private Integer id;
    /** 员工姓名 */
    private String name;
    /** 工作岗位 */
    private String position;
    /** 创建时间 */
    private LocalDateTime createTime;

    public Employee() {
    }

    public Employee(Integer id, String name, String position, LocalDateTime createTime) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
