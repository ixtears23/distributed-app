FROM openjdk:21-jdk-slim-buster as build
WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY src src

RUN ./gradlew build

FROM openjdk:21-jdk-slim-buster
VOLUME /tmp
COPY --from=build /workspace/app/build/libs/*.jar distributed-system-0.0.1.jar
ENTRYPOINT ["java","-jar","/distributed-system-0.0.1.jar"]
