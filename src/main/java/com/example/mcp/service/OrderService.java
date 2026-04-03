package com.example.mcp.service;

import com.example.mcp.entity.Order;

import java.util.List;

/**
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 查询所有订单
     */
    List<Order> getAllOrders();

    /**
     * 根据ID查询订单
     */
    Order getOrderById(String id);

    /**
     * 根据用户ID查询订单
     */
    List<Order> getOrdersByUserId(String userId);

    /**
     * 创建订单
     */
    Order createOrder(Order order);

    /**
     * 更新订单状态
     */
    boolean updateOrderStatus(String id, String status);
}
