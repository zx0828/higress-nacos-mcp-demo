package com.example.mcp.controller;

import com.example.mcp.entity.User;
import com.example.mcp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户管理 Controller
 *
 * 提供用户相关的 REST API 服务
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取所有用户
     */
    @GetMapping
    public List<User> getAllUsers() {
        log.info("Getting all users");
        return userService.getAllUsers();
    }

    /**
     * 根据 ID 获取用户
     */
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        log.info("Getting user by id: {}", id);
        return userService.getUserById(id);
    }

    /**
     * 根据名称获取用户
     */
    @GetMapping("/name/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        log.info("Getting user by name: {}", name);
        return userService.getUserByName(name);
    }

    /**
     * 创建用户
     */
    @PostMapping
    public User createUser(@RequestBody Map<String, Object> user) {
        log.info("Creating user: {}", user);
        User newUser = new User();
        newUser.setName((String) user.get("name"));
        newUser.setEmail((String) user.get("email"));
        return userService.createUser(newUser);
    }

    /**
     * 更新用户
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody Map<String, Object> user) {
        log.info("Updating user {}: {}", id, user);
        User updateUser = new User();
        updateUser.setName((String) user.get("name"));
        updateUser.setEmail((String) user.get("email"));
        return userService.updateUser(id, updateUser);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable String id) {
        log.info("Deleting user: {}", id);
        boolean deleted = userService.deleteUser(id);
        return Map.of("id", id, "deleted", deleted);
    }
}
