# 项目名称
PROJECT_NAME := aphrodite-kt

# Docker 镜像名称
IMAGE_NAME := $(PROJECT_NAME):latest

# Docker 容器名称
CONTAINER_NAME := $(PROJECT_NAME)-container

# 构建 Docker 镜像
build:
	@echo "Building Docker image..."
	docker build -t $(IMAGE_NAME) .

# 运行 Docker 容器
run:
	@echo "Running Docker container..."
	docker run -d --name $(CONTAINER_NAME) $(IMAGE_NAME)

# 停止并删除 Docker 容器
stop:
	@echo "Stopping and removing Docker container..."
	docker stop $(CONTAINER_NAME) || true
	docker rm $(CONTAINER_NAME) || true

# 删除 Docker 镜像
rmi:
	@echo "Removing Docker image..."
	docker rmi $(IMAGE_NAME)

# 查看 Docker 容器日志
logs:
	@echo "Showing logs for Docker container..."
	docker logs -f $(CONTAINER_NAME)

# 进入 Docker 容器的 shell
shell:
	@echo "Entering Docker container shell..."
	docker exec -it $(CONTAINER_NAME) /bin/sh

# 清理所有未使用的 Docker 资源（镜像、容器、网络等）
clean:
	@echo "Cleaning up Docker resources..."
	docker system prune -af

# 默认目标：构建并运行 Docker 容器
all: build run

.PHONY: build run stop rmi logs shell clean all