FROM eclipse-temurin:17-alpine
ARG JAR_FILE=target/springboot-docker-grocery-exe.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "/app.jar"]
