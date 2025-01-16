# 빌드 스테이지
FROM eclipse-temurin:21-jdk-alpine as builder

WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

# 런타임 스테이지
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# 빌드된 JAR 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# (선택적) Firebase JSON 파일이 필요한 경우
COPY --from=builder /app/src/main/resources/fcm/pickup-basketball-matching-firebase-adminsdk-9dr31-d03de1697d.json /app/resources/fcm/pickup-basketball-matching-firebase-adminsdk-9dr31-d03de1697d.json

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]