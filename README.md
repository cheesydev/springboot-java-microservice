# Spring Boot Java Microservice

Java REST API microservice for tests and demos.

# Build and run with Docker

First image build may take minutes.
```
docker build -t springboot-app:1.0.0 .
docker run --rm -p 8080:8080 springboot-app:1.0.0
```
Then, you can GET the following URLs in the browser or `curl`:
```
http://localhost:8080/products
http://localhost:8080/products/10
http://localhost:8080/actuator/health
http://localhost:8080/actuator/prometheus
```