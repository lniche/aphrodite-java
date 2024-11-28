FROM openjdk:21-jdk

WORKDIR /workspace

RUN apt-get update && \
    apt-get install -y \
    maven \
    git \
    curl \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

COPY . .

EXPOSE 8000

# 设置默认命令
CMD ["/bin/bash"]
