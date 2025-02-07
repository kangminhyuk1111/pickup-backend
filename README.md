# Pick Up - 농구 매칭 서비스

Pickup은 농구 매칭이 어려운 현 상황을 해결하기 위해 만든 개인 프로젝트 입니다.

농구 매칭을 게시하고 참가하는 행위를 원활하게 돕는 앱입니다.

현재 서버는 개발중이며 추후 2월까지 앱 배포 및 서비스 예정입니다.

## 개발 스택
- Spring Boot
- JDBC Template
- MySQL
- JJWT - 자바 JWT 토큰 라이브러리
- Redis (JWT 토큰 관리)
- FCM (Firebase Cloud Messaging) - 푸시 알림 구현
- AWS EC2, AWS RDS, AWS S3 - 클라우드 서비스 인프라 구현

## 구현 목록
- [x] JWT 토큰을 저장, Redis의 TTL을 사용하여 토큰 만료 구현
- [x] 글 작성 및 코트 정보 구현, 코트 정보는 개인의 리뷰를 가지는 테이블로 존재
- [x] 코트 정보와, 유저 디바이스 정보를 DB 정규화릍 통해 해결
- [x] 각각 사용자가 마이페이지에서 매칭 신청 목록을 관리하도록 구현
- [x] 매칭시 동시에 요청이 되는 경우를 방지하기 위해 @Transactional로 동시성 제어
- [x] 매칭이 성사되거나 매칭 수락 및 거절을 알리기 위해 FCM을 통한 푸시알림 구현

## DB 구조
![image](https://github.com/user-attachments/assets/3428f756-882d-462a-ad4d-b0f7e62b7134)

## 프로젝트 구조
- 포트 어댑터 패턴(헥사고날 아키텍처)으로 코드 리팩토링중
```
src/main/java
└── core
    └── pickupbackend
        ├── PickupBackendApplication.java
        │
        ├── auth
        │   ├── controller
        │   │   └── AuthController.java
        │   ├── domain
        │   │   └── AuthCredential.java
        │   ├── dto
        │   │   ├── LoginRequest.java
        │   │   └── LogoutRequest.java
        │   ├── filter
        │   │   ├── JwtAuthFilter.java
        │   │   └── UrlWhiteListChecker.java
        │   ├── provider
        │   │   ├── JjwtTokenProvider.java
        │   │   ├── JtiProvider.java
        │   │   ├── KeyProvider.java
        │   │   ├── ResourcesKeyProvider.java
        │   │   ├── TokenProvider.java
        │   │   └── UuidJtiProvider.java
        │   ├── repository
        │   │   └── JwtRepository.java
        │   └── service
        │       ├── AuthService.java
        │       └── JwtService.java
        │
        ├── court
        │   ├── controller
        │   │   └── CourtController.java
        │   ├── domain
        │   │   ├── Court.java
        │   │   └── CourtReview.java
        │   ├── mapper
        │   │   ├── CourtReviewRowMapper.java
        │   │   └── CourtRowMapper.java
        │   ├── repository
        │   │   ├── CourtRepository.java
        │   │   ├── CourtReviewRepository.java
        │   │   ├── JdbcCourtRepository.java
        │   │   └── JdbcCourtReviewRepository.java
        │   └── service
        │       └── CourtService.java
        │
        ├── device
        │   ├── application
        │   │   ├── in
        │   │   │   ├── DeleteDeviceTokenUseCase.java
        │   │   │   ├── DeviceService.java
        │   │   │   └── SaveDeviceTokenUseCase.java
        │   │   ├── out
        │   │   │   └── DeviceRepository.java
        │   │   └── service
        │   │       └── DefaultDeviceService.java
        │   ├── domain
        │   │   ├── Device.java
        │   │   ├── mapper
        │   │   │   └── DeviceMapper.java
        │   │   └── type
        │   │       └── DeviceType.java
        │   ├── dto
        │   │   ├── CreateDeviceDto.java
        │   │   ├── DeleteDeviceRequestDto.java
        │   │   ├── DeviceUnregisterRequest.java
        │   │   ├── FindByTokenRequest.java
        │   │   ├── FindDeviceByMemberIdRequestDto.java
        │   │   └── UpdateDeviceReqeustDto.java
        │   └── infra
        │       ├── repository
        │       │   └── JdbcDeviceRepository.java
        │       └── web
        │           └── controller
        │               └── DeviceController.java
        │
        ├── global
        │   ├── common
        │   │   ├── code
        │   │   │   └── StatusCode.java
        │   │   └── response
        │   │       ├── BaseResponse.java
        │   │       └── ErrorResponse.java
        │   ├── config
        │   │   ├── LogbackConfig.java
        │   │   ├── PasswordConfig.java
        │   │   ├── RedisConfig.java
        │   │   ├── SwaggerConfig.java
        │   │   └── WebConfig.java
        │   └── exception
        │       ├── ApplicationException.java
        │       ├── ApplicationMatchException.java
        │       ├── DatabaseExceptionHandler.java
        │       ├── ErrorCode.java
        │       ├── GlobalExceptionHandler.java
        │       ├── MessagePushException.java
        │       └── ValidateException.java
        │
        ├── match
        │   ├── application
        │   │   ├── in
        │   │   │   ├── CreateMatchUseCase.java
        │   │   │   ├── DeleteMatchUseCase.java
        │   │   │   ├── FindAllMatchesUseCase.java
        │   │   │   ├── FindMatchByIdUseCase.java
        │   │   │   ├── FindMatchParticipationUseCase.java
        │   │   │   ├── MatchAcceptUseCase.java
        │   │   │   ├── MatchRejectedUseCase.java
        │   │   │   ├── MatchService.java
        │   │   │   └── UpdateMatchUseCase.java
        │   │   ├── out
        │   │   │   ├── MatchRepository.java
        │   │   │   └── ParticipationRepository.java
        │   │   └── service
        │   │       ├── DefaultMatchService.java
        │   │       └── DefaultParticipationService.java
        │   ├── domain
        │   │   ├── Match.java
        │   │   ├── MatchStatus.java
        │   │   ├── Participation.java
        │   │   ├── ParticipationStatus.java
        │   │   └── mapper
        │   │       ├── MatchRowMapper.java
        │   │       └── ParticipationRowMapper.java
        │   ├── dto
        │   │   ├── request
        │   │   │   ├── CreateMatchRequest.java
        │   │   │   ├── CreateParticipationRequest.java
        │   │   │   ├── UpdateMatchRequest.java
        │   │   │   └── UpdateParticipationRequest.java
        │   │   └── response
        │   │       ├── MatchParticipationResponse.java
        │   │       ├── ParticipationMemberResponse.java
        │   │       └── ParticipationWithUserResponse.java
        │   └── infra
        │       ├── repository
        │       │   ├── JdbcMatchRepository.java
        │       │   └── JdbcParticipationRepository.java
        │       └── web
        │           └── controller
        │               └── MatchController.java
        │
        ├── member
        │   ├── controller
        │   │   └── MemberController.java
        │   ├── domain
        │   │   ├── Member.java
        │   │   ├── mapper
        │   │   │   └── MemberRowMapper.java
        │   │   ├── type
        │   │   │   ├── Level.java
        │   │   │   └── Position.java
        │   │   └── vo
        │   │       └── Password.java
        │   ├── dto
        │   │   ├── request
        │   │   │   ├── AddMemberRequest.java
        │   │   │   └── UpdateMemberRequest.java
        │   │   └── response
        │   ├── repository
        │   │   ├── JdbcMemberRepository.java
        │   │   └── MemberRepository.java
        │   └── service
        │       ├── MemberService.java
        │       └── PasswordService.java
        │
        └── notification
            ├── application
            │   ├── port
            │   │   └── in
            │   │       ├── MultiNotificationUseCase.java
            │   │       ├── NotificationPort.java
            │   │       ├── SendAllNotificationUseCase.java
            │   │       └── SingleNotificationUseCase.java
            │   └── service
            │       └── FcmNotificationService.java
            ├── config
            │   └── FCMConfig.java
            ├── dto
            │   ├── request
            │   │   ├── GeneralNoticeCommand.java
            │   │   └── NotificationCommand.java
            │   └── response
            │       └── NotificationResult.java
            └── infra
                └── web
                    └── controller
                        └── NotificationController.java
```
