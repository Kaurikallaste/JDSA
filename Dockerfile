FROM amazoncorretto:23.0.2-alpine3.21
WORKDIR /app

EXPOSE 8080

COPY target/*.jar application.jar

CMD ["java", "-jar", "application.jar"]
