FROM openjdk:17-jdk-slim
LABEL authors="sayul, juan"
ARG JAR_FILE=target/tasks-0.0.1.jar
COPY ${JAR_FILE} app_tasks.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_tasks.jar"]