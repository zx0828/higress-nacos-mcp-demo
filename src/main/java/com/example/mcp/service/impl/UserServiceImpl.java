package com.example.mcp.service.impl;

import com.example.mcp.entity.User;
import com.example.mcp.mapper.UserMapper;
import com.example.mcp.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User getUserById(String id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new NoSuchElementException("用户不存在: " + id);
        }
        return user;
    }

    @Override
    public List<User> getUserByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            throw new NoSuchElementException("用户不存在: " + id);
        }
        user.setId(id);
        user.setUpdateTime(LocalDateTime.now());
        if (user.getName() == null) {
            user.setName(existingUser.getName());
        }
        if (user.getEmail() == null) {
            user.setEmail(existingUser.getEmail());
        }
        userMapper.update(user);
        return user;
    }

    @Override
    public boolean deleteUser(String id) {
        User existingUser = userMapper.selectById(id);
        if (existingUser == null) {
            throw new NoSuchElementException("用户不存在: " + id);
        }
        return userMapper.deleteById(id) > 0;
    }
}
