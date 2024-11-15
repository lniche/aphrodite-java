#Dockerfile 这里我们选用 jre环境 减少镜像的体积
FROM eclipse-temurin:21.0.4_7-jre-ubi9-minimal
MAINTAINER qingshan<qingshan@example.com>
#镜像内部创建一个app目录 用来存放jar包
WORKDIR /app
#复制jar包 到app目录下
COPY ../build/libs/aphrodite-kt-1.0.0-plain.jar  /app/api.jar
#暴露端口
EXPOSE 9001
#默认启动程序
ENTRYPOINT ["java","-jar","/app/api.jar"]