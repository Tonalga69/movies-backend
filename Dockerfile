
FROM openjdk:17-jdk-slim
ADD build/libs/demo-1.0.0.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]

