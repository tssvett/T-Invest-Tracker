FROM gradle:jdk21-alpine AS dependencies
WORKDIR /opt/app
ENV GRADLE_USER_HOME=/cache
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle :dependencies --no-daemon --stacktrace

FROM gradle:jdk21-alpine AS builder
ENV APP_HOME=/opt/app
WORKDIR $APP_HOME
COPY --from=dependencies /cache /home/gradle/.gradle
COPY --from=dependencies $APP_HOME $APP_HOME
COPY src ./src
RUN gradle :clean :bootJar --no-daemon --stacktrace

FROM eclipse-temurin:21.0.4_7-jre-alpine AS final
ENV APP_HOME=/opt/app
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]