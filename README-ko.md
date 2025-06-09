# Aphrodite Spring Boot 3 API Scaffold

[English](README.md) | [简体中文](README-zh.md) | [日本語](README-ja.md) | [한국어](README-ko.md)

Aphrodite는 Spring Boot 3를 기반으로 한 템플릿 프로젝트로, 개발자들이 빠르게 시작하고 프레임워크 사용 과정을 깊이 이해할 수 있도록 설계되었습니다. 이 프로젝트는 포괄적인 샘플 코드와 구성을
제공하여 일반적인 개발 시나리오를 쉽게 학습하고 실습할 수 있게 합니다. 또한, Aphrodite는 컨테이너 배포 템플릿도 포함하고 있어 현대적인 클라우드 환경에서 프로젝트를 쉽게 배포하고 관리할 수 있으며,
개발자들이 효율적으로 애플리케이션을 구축하고 배포하는 데 도움을 줍니다.

## 기술 스택

| 기술                                                                                                                                               | 설명                                 |
|--------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------|
| [spring-boot-starter](https://spring.io/projects/spring-boot)                                                                                    | Spring Boot의 기본 의존성                |
| [kotlin-reflect](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/)                                                                   | Kotlin 리플렉션(반사) 라이브러리              |
| [spring-boot-configuration-processor](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-configuration-processor.html) | Spring Boot 구성 프로세서                |
| [spring-boot-starter-web](https://spring.io/projects/spring-boot)                                                                                | 웹 애플리케이션 개발에 필요한 의존성 제공            |
| [spring-boot-starter-undertow](https://spring.io/projects/spring-boot)                                                                           | Undertow 임베디드 웹 서버                 |
| [spring-boot-starter-validation](https://spring.io/projects/spring-boot)                                                                         | 데이터 유효성 검사 지원                      |
| [spring-boot-starter-aop](https://spring.io/projects/spring-boot)                                                                                | AOP(관점 지향 프로그래밍) 지원                |
| [spring-boot-starter-data-redis](https://spring.io/projects/spring-data-redis)                                                                   | Redis 데이터 접근 지원                    |
| [spring-boot-starter-freemarker](https://spring.io/projects/spring-boot)                                                                         | Freemarker 템플릿 엔진 지원               |
| [lombok](https://projectlombok.org/)                                                                                                             | Java 객체 캡슐화를 간소화하는 도구              |
| [kotlinx-coroutines-core](https://kotlinlang.org/docs/coroutines-overview.html)                                                                  | Kotlin 코루틴 핵심 라이브러리                |
| [kotlinx-coroutines-reactor](https://kotlinlang.org/docs/coroutines-guide.html#reactor)                                                          | Kotlin 코루틴과 Reactor 통합 지원          |
| [spring-boot-devtools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using-boot-devtools)                            | 개발 도구, 핫 리로드 지원                    |
| [postgresql](https://jdbc.postgresql.org/)                                                                                                       | PostgreSQL 데이터베이스 드라이버             |
| [junit-platform-launcher](https://junit.org/junit5/docs/current/user-guide/#overview-platform-launcher)                                          | JUnit 플랫폼 실행기                      |
| [spring-boot-starter-test](https://spring.io/projects/spring-boot)                                                                               | Spring Boot 테스트 도구                 |
| [kotlin-test-junit5](https://kotlinlang.org/docs/junit-5.html)                                                                                   | Kotlin의 JUnit 5 테스트 지원             |
| [screw-core](https://github.com/ban-jie/screw)                                                                                                   | 데이터베이스 문서 자동 생성 도구                 |
| [knife4j-openapi3-jakarta-spring-boot-starter](https://knife4j.github.io/knife4j/)                                                               | OpenAPI 3 기반의 API 문서 생성 도구         |
| [redisson-spring-boot-starter](https://github.com/redisson/redisson)                                                                             | Redis 연동용 Spring Boot 스타터          |
| [transmittable-thread-local](https://github.com/alibaba/transmittable-thread-local)                                                              | 스레드 간에 ThreadLocal 값 전달 지원         |
| [hutool-all](https://github.com/dromara/hutool)                                                                                                  | 다양한 기능을 제공하는 Java 유틸리티 라이브러리       |
| [sa-token-spring-boot3-starter](https://github.com/dromara/sa-token)                                                                             | 인증 프레임워크 Sa-Token용 Spring Boot 스타터 |
| [sa-token-redis-jackson](https://github.com/dromara/sa-token)                                                                                    | Sa-Token의 Redis 및 Jackson 연동 지원    |
| [commons-pool2](https://commons.apache.org/proper/commons-pool/)                                                                                 | Apache Commons 연결 풀 라이브러리          |
| [mybatis-plus-spring-boot3-starter](https://mp.baomidou.com/)                                                                                    | 향상된 MyBatis용 Spring Boot 스타터       |
| [mybatis-plus-generator](https://mp.baomidou.com/guide/generator.html)                                                                           | MyBatis-Plus 코드 자동 생성기             |

## 주요 기능

- **사용자 인증 및 권한 부여**: 기본적인 사용자 로그인 및 권한 인증 기능을 제공합니다.
- **분산 락**: Redis 기반의 분산 락을 통해 분산 환경에서 자원의 안전성을 보장합니다.
- **미들웨어 지원**: 인증, 요청 로그, 크로스 도메인 처리 등 자주 사용하는 미들웨어가 내장되어 있습니다.
- **통합 출력 포맷**: 간단하고 사용하기 쉬운 API 결과 통합 출력 방식을 제공하여 API 응답 형식을 표준화하고 인터페이스 일관성을 향상시킵니다.
- **API 모듈화 설계**: 확장과 유지보수가 용이한 모듈화된 API 설계를 지원합니다.
- **Swagger 문서 통합**: 프론트엔드 개발 및 테스트를 위한 API 문서를 자동으로 생성합니다.

## 구조

```
.
├── scripts/
├── database/
├── deploy/
├── docs/
├── src/
│ ├── app/
│ ├── pkg/
├── storage/
└── README.md

```

## 로컬 실행

```bash
# 1. 프로젝트 코드 복제
git clone https://github.com/lniche/aphrodite-java.git
cd aphrodite-java

# 2. 설정 파일 편집
application.yml 파일을 수정하세요

# 3. 의존성 처리
# JDK 21이 설치되어 있는지 확인하세요
./mvn clean package

# 4. 데이터베이스 초기화
db.migrate/V1.0.0__initial_schema.sql 실행

# 5. 서비스 시작
./mvn spring-boot:run
```

## 저장소 활동

![Alt](https://repobeats.axiom.co/api/embed/77051c15d804671897e92ca568506d5a088f0dca.svg "Repobeats analytics image")

## 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.

## 감사의 말씀

모든 기여자와 후원자분들께 특별한 감사를 드립니다. 여러분의 도움이 저희에게 큰 힘이 됩니다!

