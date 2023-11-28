FROM eclipse-temurin:17-jdk-alpine
ENTRYPOINT ["java", "-jar", "Task-Management.jar"]
ARG JAR_FILE
COPY ${JAR_FILE} Task-Management.jar