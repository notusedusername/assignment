FROM gradle:jdk17 AS app-build
WORKDIR /app
COPY ./ .
RUN gradle bootJar --no-daemon

FROM openjdk:11 AS app
COPY --from=app-build /app/build/libs/assignment-*.jar /app/palindrome.jar
ENTRYPOINT ["java", "-jar", "/app/palindrome.jar"]