package com.example.mcp.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Nacos MCP 注册器
 * 
 * 在应用启动后，自动将 MCP Server 元数据注册到 Nacos 服务注册中心
 * 
 * 注意：这只是在服务元数据中添加 MCP 相关信息
 * 实际的 MCP Server 配置仍需在 Nacos 控制台手动添加
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NacosMcpRegistrar implements ApplicationListener<ApplicationReadyEvent> {

    private final NacosDiscoveryProperties discoveryProperties;

    @Value("${mcp.server.name:demo-mcp-server}")
    private String mcpServerName;

    @Value("${mcp.server.description:Nacos MCP Demo Server}")
    private String mcpServerDescription;

    @Value("${mcp.server.version:1.0.0}")
    private String mcpServerVersion;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("=== Nacos MCP Registrar Started ===");
        log.info("Application Name: {}", discoveryProperties.getService());
        log.info("Nacos Server: {}", discoveryProperties.getServerAddr());
        
        // 准备 MCP Server 元数据
        Map<String, String> mcpMetadata = buildMcpMetadata();
        
        log.info("MCP Metadata prepared:");
        mcpMetadata.forEach((key, value) -> 
            log.info("  {}: {}", key, value)
        );
        
        log.info("");
        log.info("=== 下一步操作 ===");
        log.info("1. 打开 Nacos 控制台：http://localhost:8848/nacos/");
        log.info("2. 进入：AI 管理 → MCP Server");
        log.info("3. 点击'新增'，填写以下信息：");
        log.info("   - MCP 名称：{}", mcpServerName);
        log.info("   - 描述：{}", mcpServerDescription);
        log.info("   - 版本：{}", mcpServerVersion);
        log.info("   - Base URL: http://localhost:8082");
        log.info("   - 工具：user_management, order_query");
        log.info("");
    }

    /**
     * 构建 MCP 元数据
     * 这些元数据会被添加到 Nacos 服务注册信息中
     */
    private Map<String, String> buildMcpMetadata() {
        Map<String, String> metadata = new HashMap<>();
        
        // MCP Server 基本信息
        metadata.put("mcp.server.name", mcpServerName);
        metadata.put("mcp.server.description", mcpServerDescription);
        metadata.put("mcp.server.version", mcpServerVersion);
        
        // MCP 工具信息
        metadata.put("mcp.tools.user_management", "用户管理工具 - GET,POST,PUT,DELETE /api/users");
        metadata.put("mcp.tools.order_query", "订单查询工具 - GET,POST,PATCH /api/orders");
        
        // API 端点信息
        metadata.put("mcp.api.base_url", "http://localhost:8082");
        metadata.put("mcp.api.users", "/api/users");
        metadata.put("mcp.api.orders", "/api/orders");
        
        // 协议信息
        metadata.put("mcp.protocol.version", "1.0.0");
        metadata.put("mcp.protocol.transport", "HTTP");
        
        return metadata;
    }
}
