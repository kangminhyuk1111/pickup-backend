FROM eclipse-temurin:17.0.8.1_1-jdk-jammy AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM eclipse-temurin:17.0.8.1_1-jre-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]