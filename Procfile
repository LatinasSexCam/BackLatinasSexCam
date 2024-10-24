FROM openjdk:21-jdk-slim

WORKDIR /app

COPY build/libs/LatinasSexCam.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]