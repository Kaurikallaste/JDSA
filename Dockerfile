FROM amazoncorretto:23.0.2-alpine3.21
WORKDIR /app

EXPOSE 8080

COPY target/jdsa-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "jdsa-0.0.1-SNAPSHOT.jar"]
