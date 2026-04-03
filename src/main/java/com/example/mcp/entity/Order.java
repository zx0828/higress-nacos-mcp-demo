package com.example.mcp.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体类
 */
public class Order {
    /** 订单ID */
    private String id;
    /** 用户ID */
    private String userId;
    /** 产品名称 */
    private String product;
    /** 数量 */
    private Integer quantity;
    /** 价格 */
    private BigDecimal price;
    /** 订单状态: pending, shipped, completed */
    private String status;
    /** 创建时间 */
    private LocalDateTime createTime;

    public Order() {
    }

    public Order(String id, String userId, String product, Integer quantity, BigDecimal price, String status, LocalDateTime createTime) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
