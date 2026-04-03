package com.example.mcp.service.impl;

import com.example.mcp.entity.Order;
import com.example.mcp.mapper.OrderMapper;
import com.example.mcp.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * 订单服务实现
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.selectAll();
    }

    @Override
    public Order getOrderById(String id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return orderMapper.selectByUserId(userId);
    }

    @Override
    public Order createOrder(Order order) {
        order.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase());
        order.setCreateTime(LocalDateTime.now());
        if (order.getStatus() == null) {
            order.setStatus("pending");
        }
        orderMapper.insert(order);
        return order;
    }

    @Override
    public boolean updateOrderStatus(String id, String status) {
        return orderMapper.updateStatus(id, status) > 0;
    }
}
