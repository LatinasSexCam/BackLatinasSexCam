FROM openjdk:21-jdk-alpine

WORKDIR /app

COPY build/libs/LatinasSexCam.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]