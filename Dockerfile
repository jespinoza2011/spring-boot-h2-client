FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/spring-boot-h2-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /app.jar
CMD ["java", "-jar", "/app.jar"]