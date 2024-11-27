FROM eclipse-temurin:21

WORKDIR /app

COPY ./target/aphrodite-java-1.0.0.jar  /app/api.jar

EXPOSE 8000

ENTRYPOINT ["java","-jar","/app/api.jar"]
