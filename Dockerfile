# 빌드 스테이지
FROM gradle:7.6.1-jdk17 AS builder
WORKDIR /build

# 그래들 파일들을 먼저 복사하여 의존성을 캐시
COPY build.gradle settings.gradle /build/
COPY gradle /build/gradle
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 소스 복사 및 빌드
COPY . /build
RUN gradle clean build -x test --parallel

# 실행 스테이지
FROM openjdk:17-slim
WORKDIR /app

# 빌드 결과물 복사
COPY --from=builder /build/build/libs/*.jar app.jar

# 컨테이너 실행 시 실행할 명령어
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]