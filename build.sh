#!/bin/bash

# =============================================================================
# 构建和部署脚本
# =============================================================================
# 功能：Maven 打包 + Docker 构建 + 容器管理
#
# 使用方法:
#   ./build.sh                    # 完整构建所有服务
#   ./build.sh package            # 仅 Maven 打包
#   ./build.sh build              # 打包 + 构建所有镜像
#   ./build.sh build-ai           # 仅构建 AI namespace 镜像
#   ./build.sh build-public       # 仅构建 Public namespace 镜像
#   ./build.sh up                 # 启动所有服务
#   ./build.sh up-ai              # 仅启动 AI namespace 服务
#   ./build.sh up-public          # 仅启动 Public namespace 服务
#   ./build.sh down               # 停止所有服务
#   ./build.sh status             # 查看服务状态
#   ./build.sh logs [service]     # 查看日志
# =============================================================================

set -e

COMPOSE_FILE="docker-compose.yml"

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

# Docker 构建所有镜像
build_all() {
  echo "=== 2/3 构建所有 Docker 镜像 ==="
  docker-compose -f $COMPOSE_FILE build
  echo "✅ 所有镜像构建完成"
  echo ""
}

# 构建 AI namespace 镜像
build_ai() {
  echo "=== 2/3 构建 AI Namespace 镜像 ==="
  docker-compose -f $COMPOSE_FILE build nacos-mcp-demo-ai
  echo "✅ AI 镜像构建完成 (tag: ai-latest)"
  echo ""
}

# 构建 Public namespace 镜像
build_public() {
  echo "=== 2/3 构建 Public Namespace 镜像 ==="
  docker-compose -f $COMPOSE_FILE build nacos-mcp-demo-public
  echo "✅ Public 镜像构建完成 (tag: public-latest)"
  echo ""
}

# 启动所有服务
up_all() {
  echo "=== 3/3 启动所有服务 ==="
  docker-compose -f $COMPOSE_FILE up -d
  echo "✅ 所有服务已启动"
  echo ""
  show_status
}

# 启动 AI namespace 服务
up_ai() {
  echo "=== 启动 AI Namespace 服务 ==="
  docker-compose -f $COMPOSE_FILE up -d nacos-mcp-demo-ai
  echo "✅ AI 服务已启动"
  echo ""
  show_status
}

# 启动 Public namespace 服务
up_public() {
  echo "=== 启动 Public Namespace 服务 ==="
  docker-compose -f $COMPOSE_FILE up -d nacos-mcp-demo-public
  echo "✅ Public 服务已启动"
  echo ""
  show_status
}

# 停止所有服务
down_all() {
  echo "=== 停止所有服务 ==="
  docker-compose -f $COMPOSE_FILE down
  echo "✅ 所有服务已停止"
  echo ""
}

# 显示服务状态
show_status() {
  echo "等待服务启动..."
  sleep 5

  echo "=== 服务状态 ==="
  docker ps --filter "name=nacos-mcp-demo" --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
  echo ""

  echo "=== 最近日志 (AI 服务) ==="
  docker logs nacos-mcp-demo-ai --tail 5 2>&1 | grep -E "(Started|register)" || true
  echo ""
}

# 显示帮助
show_help() {
  echo "用法: $0 {package|build|build-ai|build-public|up|up-ai|up-public|down|status|logs|all}"
  echo ""
  echo "  构建命令:"
  echo "    package      - 仅 Maven 打包"
  echo "    build        - 打包 + 构建所有镜像"
  echo "    build-ai     - 仅构建 AI namespace 镜像"
  echo "    build-public - 仅构建 Public namespace 镜像"
  echo ""
  echo "  服务管理:"
  echo "    up           - 启动所有服务"
  echo "    up-ai        - 仅启动 AI namespace 服务"
  echo "    up-public    - 仅启动 Public namespace 服务"
  echo "    down         - 停止所有服务"
  echo "    status       - 查看服务状态"
  echo "    logs [name]  - 查看服务日志"
  echo ""
  echo "  默认:"
  echo "    all          - 完整构建并启动所有服务（默认）"
  echo ""
  echo "  服务说明:"
  echo "    nacos-mcp-demo-ai     - AI namespace, 端口 8082"
  echo "    nacos-mcp-demo-public - Public namespace, 端口 8083"
  exit 1
}

# 查看日志
show_logs() {
  local service=${1:-nacos-mcp-demo-ai}
  docker logs -f $service
}

# 主流程
case "${1:-all}" in
  package)
    package
    ;;
  build)
    package
    build_all
    ;;
  build-ai)
    package
    build_ai
    ;;
  build-public)
    package
    build_public
    ;;
  up)
    up_all
    ;;
  up-ai)
    up_ai
    ;;
  up-public)
    up_public
    ;;
  down)
    down_all
    ;;
  status)
    show_status
    ;;
  logs)
    show_logs "$2"
    ;;
  all|"")
    package
    build_all
    up_all
    ;;
  *)
    show_help
    ;;
esac

echo "================================================================"
echo "  构建完成!"
echo "================================================================"
echo ""
echo "服务地址:"
echo "  AI (8082):     http://localhost:8082"
echo "  Public (8083): http://localhost:8083"
echo ""
echo "API 测试:"
echo "  用户: curl http://localhost:8082/api/users"
echo "  订单: curl http://localhost:8082/api/orders"
echo "  员工: curl 'http://localhost:8082/api/employee/position?name=张伟'"
echo ""
