#
# builder
#
FROM openjdk:8-alpine AS builder

RUN addgroup -S appbuilder && adduser -S -G appbuilder appbuilder
USER appbuilder
WORKDIR /home/appbuilder

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# download all dependencies before hand, takes a few minutes!
RUN ./mvnw dependency:go-offline

# and then, copy project files to build
# most likely subsequent builds will be triggered by changes in src
COPY src src

# build, run tests and create the package file `target/springboot-app.jar`
RUN ./mvnw install

#
# dist
#
FROM openjdk:8-jre-alpine AS dist

RUN addgroup -S appuser && adduser -S -G appuser appuser
USER appuser
WORKDIR /home/appuser

COPY --from=builder --chown=appuser /home/appbuilder/target/springboot-app.jar .
CMD ["java", "-jar", "springboot-app.jar"]
