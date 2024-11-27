PROJECT_NAME := aphrodite-java

IMAGE_NAME := $(PROJECT_NAME):latest

CONTAINER_NAME := $(PROJECT_NAME)-container

build:
	@echo "Building Docker image..."
	docker build -t $(IMAGE_NAME) -f deploy/Dockerfile .

run:
	@echo "Running Docker container..."
	docker run -d --name $(CONTAINER_NAME) $(IMAGE_NAME)

stop:
	@echo "Stopping and removing Docker container..."
	docker stop $(CONTAINER_NAME) || true
	docker rm $(CONTAINER_NAME) || true

rmi:
	@echo "Removing Docker image..."
	docker rmi $(IMAGE_NAME)

logs:
	@echo "Showing logs for Docker container..."
	docker logs -f $(CONTAINER_NAME)

shell:
	@echo "Entering Docker container shell..."
	docker exec -it $(CONTAINER_NAME) /bin/sh

clean:
	@echo "Cleaning up Docker resources..."
	docker system prune -af

all: build run

.PHONY: build run stop rmi logs shell clean all
