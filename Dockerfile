FROM openjdk:11.0.14.1-jdk-slim-bullseye

WORKDIR /app

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]

COPY target/dynamo-db-demo-0.0.1-SNAPSHOT.jar app.jar