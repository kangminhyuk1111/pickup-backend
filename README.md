# Pick Up - 농구 매칭 서비스

Pickup은 농구 매칭이 어려운 현 상황을 해결하기 위해 만든 개인 프로젝트 입니다.

농구 매칭을 게시하고 참가하는 행위를 원활하게 돕는 앱입니다.

현재 서버는 개발중이며 추후 2월까지 앱 배포 및 서비스 예정입니다.

## 개발 스택
- Spring Boot
- MySQL
- Redis (JWT 토큰 관리)
- FCM (Firebase Cloud Messaging) - 푸시 알림 구현

## 구현 목록
- [ ] JWT 토큰을 저장, Redis의 TTL을 사용하여 토큰 만료 구현
- [ ] 글 작성 및 코트 정보 구현, 코트 정보는 개인의 리뷰를 가지는 테이블로 존재
- [ ] 코트 정보와, 유저 디바이스 정보를 DB 정규화릍 통해 해결
- [ ] 각각 사용자가 마이페이지에서 매칭 신청 목록을 관리하도록 구현
- [ ] 매칭시 동시에 요청이 되는 경우를 방지하기 위해 @Transactional로 동시성 제어
- [ ] 매칭이 성사되거나 매칭 수락 및 거절을 알리기 위해 FCM을 통한 푸시알림 구현    
