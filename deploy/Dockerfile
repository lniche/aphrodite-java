FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY ./target/aphrodite-java-1.0.0.jar  /app/api.jar

EXPOSE 8000

CMD ["java","-jar","/app/api.jar"]

