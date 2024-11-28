FROM openjdk:21-jdk

WORKDIR /workspace

RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

COPY . .

RUN mvn install -DskipTests

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]
