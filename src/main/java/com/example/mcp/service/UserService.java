package com.example.mcp.service;

import com.example.mcp.entity.User;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 查询所有用户
     */
    List<User> getAllUsers();

    /**
     * 根据ID查询用户
     */
    User getUserById(String id);

    /**
     * 根据姓名模糊查询用户
     */
    List<User> getUserByName(String name);

    /**
     * 创建用户
     */
    User createUser(User user);

    /**
     * 更新用户
     */
    User updateUser(String id, User user);

    /**
     * 删除用户
     */
    boolean deleteUser(String id);
}
