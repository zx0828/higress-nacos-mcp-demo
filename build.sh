#!/bin/bash

# =============================================================================
# 构建和部署脚本
# =============================================================================
# 功能：Maven 打包 + Docker 构建 + 容器重启
#
# 使用方法: 
#   ./build.sh          # 完整构建（打包+构建镜像+重启）
#   ./build.sh package  # 仅 Maven 打包
#   ./build.sh build    # 仅构建 Docker 镜像
#   ./build.sh restart  # 仅重启容器
# =============================================================================

set -e

echo "================================================================"
echo "  Nacos MCP Demo 构建脚本"
echo "================================================================"
echo ""

# Maven 打包
package() {
  echo "=== 1/3 Maven 打包 ==="
  mvn clean package -DskipTests
  echo "✅ 打包完成"
  echo ""
}

# Docker 构建镜像
build_image() {
  echo "=== 2/3 构建 Docker 镜像 ==="
  docker-compose build
  echo "✅ 镜像构建完成"
  echo ""
}

# 重启容器
restart() {
  echo "=== 3/3 重启容器 ==="
  docker-compose down
  docker-compose up -d
  echo "✅ 容器已重启"
  echo ""
  
  echo "等待服务启动..."
  sleep 5
  
  echo "=== 服务状态 ==="
  docker ps --filter "name=nacos-mcp-demo" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
  echo ""
  
  echo "=== 最近日志 ==="
  docker logs nacos-mcp-demo --tail 10
  echo ""
}

# 主流程
case "${1:-all}" in
  package)
    package
    ;;
  build)
    package
    build_image
    ;;
  restart)
    restart
    ;;
  all|"")
    package
    build_image
    restart
    ;;
  *)
    echo "用法: $0 {package|build|restart|all}"
    echo ""
    echo "  package  - 仅 Maven 打包"
    echo "  build    - 打包 + 构建 Docker 镜像"
    echo "  restart  - 仅重启容器"
    echo "  all      - 完整构建（默认）"
    exit 1
    ;;
esac

echo "================================================================"
echo "  构建完成!"
echo "================================================================"
echo ""
echo "服务地址: http://localhost:8082"
echo "API 测试:"
echo "  用户: curl http://localhost:8082/api/users"
echo "  订单: curl http://localhost:8082/api/orders"
echo "  员工: curl 'http://localhost:8082/api/employee/position?name=张伟'"
echo ""
