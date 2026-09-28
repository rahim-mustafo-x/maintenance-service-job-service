# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /build

COPY gradlew gradlew
COPY gradle/ gradle/
COPY build.gradle settings.gradle ./

RUN chmod +x gradlew

RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew dependencies --no-daemon

COPY src/ src/

RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew bootJar --no-daemon && \
    mv build/libs/*.jar build/libs/app.jar


FROM eclipse-temurin:17-jre-jammy AS final

ARG UID=10001

RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    appuser

WORKDIR /app

COPY --from=build /build/build/libs/app.jar app.jar

USER appuser

EXPOSE 7876

ENTRYPOINT ["java", "-jar", "app.jar"]