FROM openjdk:21-slim

WORKDIR /echo_bot

COPY ../build/libs/*.jar app.jar

EXPOSE 9040

ENTRYPOINT ["java", "-jar", "app.jar"]