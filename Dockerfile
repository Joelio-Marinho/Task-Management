FROM openjdk:8-jre
ENTRYPOINT ["/usr/bin/java", "-jar", "Task-Management.jar"]
ARG JAR_FILE
ADD target/${JAR_FILE} Task-Management.jar