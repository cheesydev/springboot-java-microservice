#
# builder
#
FROM openjdk:11-jdk-slim AS builder

RUN groupadd -g 1000 appbuilder && useradd -m -u 1000 -g appbuilder appbuilder
USER appbuilder
WORKDIR /home/appbuilder

COPY gradle/ gradle/
COPY gradlew .
COPY build.gradle settings.gradle ./

COPY src src
RUN ./gradlew --no-daemon test bootJar

#
# dist
#
FROM openjdk:11-jdk-slim AS dist

RUN groupadd -g 1000 appuser && useradd -m -u 1000 -g appuser appuser
USER appuser
WORKDIR /home/appuser

COPY --from=builder --chown=appuser /home/appbuilder/build/libs/springboot-app.jar .
CMD ["java", "-jar", "springboot-app.jar"]
