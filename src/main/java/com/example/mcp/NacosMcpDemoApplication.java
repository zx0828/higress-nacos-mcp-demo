package com.example.mcp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Nacos MCP Demo Application
 *
 * 功能说明：
 * 1. 注册到 Nacos 服务注册中心
 * 2. 提供 MCP Server 能力
 * 3. 提供用户管理、订单查询、员工岗位查询服务（基于 MySQL + MyBatis）
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.example.mcp.mapper")
public class NacosMcpDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosMcpDemoApplication.class, args);
    }
}
