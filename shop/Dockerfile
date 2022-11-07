FROM eclipse-temurin:17-alpine
ARG JAR_FILE=shop/target/springboot-docker-shop-exe.jar
ADD ${JAR_FILE} apps.jar
ENTRYPOINT ["java","-jar", "apps.jar"]