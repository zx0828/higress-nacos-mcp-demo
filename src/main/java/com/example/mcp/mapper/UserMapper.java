package com.example.mcp.mapper;

import com.example.mcp.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户 Mapper 接口
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     */
    List<User> selectAll();

    /**
     * 根据ID查询用户
     */
    User selectById(@Param("id") String id);

    /**
     * 根据姓名模糊查询用户
     */
    List<User> selectByName(@Param("name") String name);

    /**
     * 插入用户
     */
    int insert(User user);

    /**
     * 更新用户
     */
    int update(User user);

    /**
     * 删除用户
     */
    int deleteById(@Param("id") String id);
}
