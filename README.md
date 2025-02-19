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

## 업데이트 내역
#### 20250212 - 서버 업데이트
Response타입 변경
- 기존 code, status, data 타입에서, data만 반환하고 응답 헤더에 코드로 들어가도록 변경

매치 참여 수락 및 거절 로직 변경
- 기존 participationId와 uri로 구분하던 부분을 지금은 body에 status 까지 함께 넣어서 보내면 처리
/status - POST로 통일 

#### 20250214 - 백엔드 업데이트 사항
PUT /member 엔드포인트 수정 요청 건 - password 필드를 optional 처리
- 매치 생성자의 참여 제한 관련 API 수정 요청 - 매치 생성자가 자신의 매치에 신청시 신청할 수 없음을 반환

#### 20250217 - 백엔드 업데이트 사항
회원탈퇴 api - cascade 설정을 추가하여 회원이 삭제되면 관련된 매칭도 같이 삭제되고 api도 정상작동 확인
- 매칭목록 api - jwt payload에 userId를 추가하여 앞단으로 반환시키면 앞단에서는 jwt를 복호화해서 payload를 꺼내서 사용

#### 20250219 - 백엔드 업데이트 사항
페이징 처리 - 매치에 페이징 처리를 적용하였습니다. 페이징은 page와 size파라미터를 받습니다.
- 만약 /matches?page=0&size=10 이라면 0번째 페이지부터 10개 보여달라는 뜻으로 0번째부터 9번째까지 총 10개가 반환됩니다.
- 만약 /matches?page=1&size=10 이라면 1x10 = 10번째 까지 건너뛰고 10번째부터 19번째까지 총 10개를 반환합니다.
- page x size 부터 건너뛰고 size만큼 반환한다고 생각하시면 됩니다.

코트 이미지 및 부대시설 업데이트 - 코트 api 호출시 이미지 url과 부대시설 정보를 추가했습니다.
- 이미지는 zip파일을 풀어서 프론트엔드 프로젝트에 넣어서 사용하면 됩니다.

위치 관련 DB 수정 - 현재 matches api에 서울특별시의 구 list를 받는 api를 하나 추가했습니다. 이걸로 select box를 구현하셔도되고, 프론트에서 자체적으로 구현해도됩니다. 
- 기존 location을 district와 locationDetail로 바꿨습니다.
- district는 서울특별시 동작구 <- 이런식으로 해주시고
- locationDetail을 varchar(200) 이니 세부적인 장소 기입하도록 해주시면 될 것 같습니다. ex) 동작공원 농구코트 3번째

## DB 구조
![image](https://github.com/user-attachments/assets/faa08ae4-f110-41ef-bdb2-8f594dd7b1ac)


