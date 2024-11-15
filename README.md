# Aphrodite Spring Boot 3 API Scaffold

[English](README.md) | [简体中文](README-zh.md)

Aphrodite is a template project based on Spring Boot 3, designed to help developers get started
quickly and gain a deep understanding of the framework's usage process. The project provides comprehensive sample code
and configuration, covering common development scenarios for easy learning and practice. In addition, Aphrodite also
includes a container deployment template, which makes the project easy to deploy and manage in a modern cloud
environment, helping developers to efficiently build and release applications.

## Tech Stack

| Technology                                                                                                                                       | Description                                                    |
|--------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------|
| [spring-boot-starter](https://spring.io/projects/spring-boot)                                                                                    | Spring Boot's basic dependencies                               |
| [kotlin-reflect](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/)                                                                   | Kotlin reflection library                                      |
| [spring-boot-configuration-processor](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-configuration-processor.html) | Spring Boot configuration processor                            |
| [spring-boot-starter-web](https://spring.io/projects/spring-boot)                                                                                | Provides dependencies required for web application development |
| [spring-boot-starter-undertow](https://spring.io/projects/spring-boot)                                                                           | Undertow embedded web server                                   |
| [spring-boot-starter-validation](https://spring.io/projects/spring-boot)                                                                         | Data validation support                                        |
| [spring-boot-starter-aop](https://spring.io/projects/spring-boot)                                                                                | AOP (Aspect-Oriented Programming) support                      |
| [spring-boot-starter-data-redis](https://spring.io/projects/spring-data-redis)                                                                   | Redis data access support                                      |
| [spring-boot-starter-freemarker](https://spring.io/projects/spring-boot)                                                                         | Freemarker template engine support                             |
| [lombok](https://projectlombok.org/)                                                                                                             | Tools to simplify Java object encapsulation                    |
| [kotlinx-coroutines-core](https://kotlinlang.org/docs/coroutines-overview.html)                                                                  | Kotlin coroutine core library                                  |
| [kotlinx-coroutines-reactor](https://kotlinlang.org/docs/coroutines-guide.html#reactor)                                                          | Kotlin coroutines and Reactor integration                      |
| [spring-boot-devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using-boot-devtools)                            | Development tools, support hot reload                          |
| [postgresql](https://jdbc.postgresql.org/)                                                                                                       | PostgreSQL database driver                                     |
| [junit-platform-launcher](https://junit.org/junit5/docs/current/user-guide/#overview-platform-launcher)                                          | JUnit platform launcher                                        |
| [spring-boot-starter-test](https://spring.io/projects/spring-boot)                                                                               | Spring Boot test tool                                          |
| [kotlin-test-junit5](https://kotlinlang.org/docs/junit-5.html)                                                                                   | Kotlin test supports JUnit 5                                   |
| [screw-core](https://github.com/ban-jie/screw)                                                                                                   | Database document generation tool                              |
| [knife4j-openapi3-jakarta-spring-boot-starter](https://knife4j.github.io/knife4j/)                                                               | Document generation tool supported by OpenAPI 3                |
| [redisson-spring-boot-starter](https://github.com/redisson/redisson)                                                                             | Spring Boot starter for Redis                                  |
| [transmittable-thread-local](https://github.com/alibaba/transmittable-thread-local)                                                              | Supports thread local variables passed across threads          |
| [hutool-all](https://github.com/dromara/hutool)                                                                                                  | Java tool library, providing a variety of common functions     |
| [sa-token-spring-boot3-starter](https://github.com/dromara/sa-token)                                                                             | Spring Boot starter for the authentication framework           |
| [sa-token-redis-jackson](https://github.com/dromara/sa-token)                                                                                    | Redis and Jackson integration support for Sa-Token             |
| [commons-pool2](https://commons.apache.org/proper/commons-pool/)                                                                                 | Apache Commons connection pool                                 |
| [mybatis-plus-spring-boot3-starter](https://mp.baomidou.com/)                                                                                    | Enhanced Spring Boot starter for MyBatis                       |
| [mybatis-plus-generator](https://mp.baomidou.com/guide/generator.html)                                                                           | MyBatis-Plus code generator                                    |

## Features

- **User authentication and authorization**: Provides basic user login and permission authorization functions.
- **Distributed lock**: Distributed lock based on Redis to ensure resource security in a distributed environment.
- **Middleware support**: Built-in commonly used middleware, including authentication, request logs, cross-domain
  processing, etc.
- **Unified output format**: Provides a simple and easy-to-use API Result unified output method, standardizes the API
  response format, and improves interface consistency.
- **API modular design**: Supports modular API design, which is easy to expand and maintain.
- **Swagger document integration**: Automatically generates API documents for front-end development and testing.

## Structure

```
.
├── bin/ # executable scripts
├── database/ # database related
├── deploy/ # deployment related files
├── docs/ # project documentation
├── src/ # core directory
│ ├── app/ # application core code
│ ├── pkg/ # public module
├── storage/ # file storage
└── README.md # project description

```

## Run Local

```bash
# 1. Clone the project code base
git clone https://github.com/lniche/aphrodite-java.git
cd aphrodite-java

# 2. Configuration file
edit application.yml

# 3. Handle dependencies
# Make sure you have JDK21 installed
mvn clean package

# 4. Initialize the database
database/init.sql

# 5. Start the service
mvn spring-boot:run
```

## Repo Activity

![Alt](https://repobeats.axiom.co/api/embed/77051c15d804671897e92ca568506d5a088f0dca.svg "Repobeats analytics image")

## Contribute

If you have any suggestions or ideas, please create an issue or submit a Pull Request directly.

1. **Fork** this repository.
2. **Create** a new branch:

```
git checkout -b feature/your-feature
```

3. **Commit** your changes:

```
git commit -m 'Add new feature'
```

4. **Push** to your branch:

```
git push origin feature/your-feature
```

5. **Submit** a Pull Request.

## License

This project is licensed under the MIT License.

## Acknowledgements

Special thanks to all contributors and supporters, your help is essential to us!
