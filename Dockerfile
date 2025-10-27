FROM amazoncorretto:24

COPY target/APS-2-ArqObj-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]