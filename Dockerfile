FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY target/nacos-mcp-demo-1.0.0.jar app.jar

# 默认暴露 8082，可通过环境变量覆盖
ENV APP_PORT=8082
EXPOSE ${APP_PORT}

# 支持通过环境变量指定 Spring Profile
ENV SPRING_PROFILES_ACTIVE=""

# 动态 ENTRYPOINT：如果设置了 SPRING_PROFILES_ACTIVE，则使用 --spring.profiles.active
ENTRYPOINT ["sh", "-c", "java -jar app.jar $([ -n \"$SPRING_PROFILES_ACTIVE\" ] && echo \"--spring.profiles.active=$SPRING_PROFILES_ACTIVE\")"]
