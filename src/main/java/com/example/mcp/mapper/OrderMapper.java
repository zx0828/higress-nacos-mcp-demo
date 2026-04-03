package com.example.mcp.mapper;

import com.example.mcp.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单 Mapper 接口
 */
@Mapper
public interface OrderMapper {

    /**
     * 查询所有订单
     */
    List<Order> selectAll();

    /**
     * 根据ID查询订单
     */
    Order selectById(@Param("id") String id);

    /**
     * 根据用户ID查询订单
     */
    List<Order> selectByUserId(@Param("userId") String userId);

    /**
     * 插入订单
     */
    int insert(Order order);

    /**
     * 更新订单状态
     */
    int updateStatus(@Param("id") String id, @Param("status") String status);
}
