.PHONY: build
init:
	docker build -f deploy/Dockerfile -t aphrodite-java:latest .

.PHONY: deploy
init:
	docker-compose -f deploy/docker-compose.yml up
