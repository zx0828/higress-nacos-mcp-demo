package com.example.mcp.controller;

import com.example.mcp.entity.Order;
import com.example.mcp.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 订单管理 Controller
 *
 * 提供订单相关的 REST API 服务
 */
@Slf4j
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 获取所有订单
     */
    @GetMapping
    public List<Order> getAllOrders() {
        log.info("Getting all orders");
        return orderService.getAllOrders();
    }

    /**
     * 根据 ID 获取订单
     */
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        log.info("Getting order by id: {}", id);
        Order order = orderService.getOrderById(id);
        if (order == null) {
            throw new NoSuchElementException("订单不存在: " + id);
        }
        return order;
    }

    /**
     * 根据用户 ID 获取订单
     */
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable String userId) {
        log.info("Getting orders by userId: {}", userId);
        return orderService.getOrdersByUserId(userId);
    }

    /**
     * 创建订单
     */
    @PostMapping
    public Order createOrder(@RequestBody Map<String, Object> requestBody) {
        log.info("Creating order: {}", requestBody);
        Order order = new Order();
        order.setUserId((String) requestBody.get("userId"));
        order.setProduct((String) requestBody.get("product"));
        order.setQuantity((Integer) requestBody.get("quantity"));
        Object priceObj = requestBody.get("price");
        if (priceObj instanceof Number) {
            order.setPrice(new BigDecimal(priceObj.toString()));
        }
        order.setStatus((String) requestBody.getOrDefault("status", "pending"));
        return orderService.createOrder(order);
    }

    /**
     * 更新订单状态
     */
    @PatchMapping("/{id}/status")
    public Map<String, Object> updateOrderStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> requestBody) {
        log.info("Updating order {} status: {}", id, requestBody);
        String status = requestBody.get("status");
        boolean updated = orderService.updateOrderStatus(id, status);
        return Map.of("id", id, "status", status, "updated", updated);
    }
}
