CREATE TABLE court_reviews (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         court_id BIGINT NOT NULL,                    -- 농구장 ID (외래키)
                         user_id BIGINT NOT NULL,                     -- 사용자 ID (외래키)
                         rating TINYINT NOT NULL,                     -- 평점 (1-5)
                         content TEXT NOT NULL,                       -- 리뷰 내용
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 작성일
                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 수정일
                         is_deleted BOOLEAN DEFAULT FALSE,            -- 삭제 여부

                         CONSTRAINT rating_range CHECK (rating >= 1 AND rating <= 5),
                         CONSTRAINT fk_review_court FOREIGN KEY (court_id) REFERENCES courts(id),
                         CONSTRAINT fk_review_user FOREIGN KEY (user_id) REFERENCES users(id),
                         INDEX idx_court_id (court_id),              -- 농구장별 리뷰 조회를 위한 인덱스
                         INDEX idx_user_id (user_id)                 -- 사용자별 리뷰 조회를 위한 인덱스
);

-- 테스트 리뷰 데이터 삽입
INSERT INTO court_reviews
(court_id, user_id, rating, content, created_at)
VALUES
-- 첫 번째 코트의 리뷰들
(1, 11, 5, '코트 상태가 매우 좋고 조명이 밝아서 저녁에도 플레이하기 좋습니다. 주변에 편의점도 있어서 편리해요.', '2024-01-15 14:30:00'),
(1, 11, 4, '주차 공간이 넓어서 좋습니다. 바닥 상태도 괜찮은 편이에요.', '2024-02-01 16:45:00'),
(1, 11, 3, '주말에는 사람이 많아서 기다려야 할 수 있어요. 평일 저녁이 추천합니다.', '2024-02-20 19:20:00'),

-- 두 번째 코트의 리뷰들
(2, 11, 4, '농구대 상태가 좋고 골대 그물도 새것이에요. 추천합니다!', '2024-01-20 15:10:00'),
(2, 11, 5, '바닥이 미끄럽지 않고 라인도 선명해서 좋았어요. 저녁에도 조명이 밝습니다.', '2024-02-05 20:30:00'),
(2, 11, 4, '주변에 편의시설이 잘 갖춰져 있어요. 화장실도 깨끗합니다.', '2024-03-01 17:15:00'),

-- 세 번째 코트의 리뷰들
(3, 11, 5, '최근에 리모델링해서 시설이 아주 좋아요. 평일 아침에 가면 거의 사람이 없어요.', '2024-01-25 09:00:00'),
(3, 11, 4, '농구대 높이가 정확하고 백보드도 상태가 좋습니다. 다만 주차가 좀 불편해요.', '2024-02-10 13:20:00'),
(3, 11, 3, '주말에는 꽤 붐비는 편입니다. 평일 이용을 추천드려요.', '2024-03-15 18:45:00'),

-- 네 번째 코트의 리뷰들
(4, 11, 4, '실외 코트임에도 관리가 잘 되어있어요. 저녁에도 조명이 밝습니다.', '2024-02-15 19:30:00'),
(4, 11, 5, '주변 환경이 조용하고 쾌적해서 좋아요. 농구하기 좋은 곳입니다.', '2024-03-01 16:00:00'),
(4, 11, 4, '코트 상태가 좋고 백보드 반응도 좋습니다. 추천해요!', '2024-03-20 14:15:00'),

-- 다섯 번째 코트의 리뷰들
(5, 11, 5, '새로 생긴 코트라 시설이 매우 좋아요. 주차도 편리합니다.', '2024-02-20 11:00:00'),
(5, 11, 4, '바닥 상태가 좋고 배수도 잘 됩니다. 비 온 다음날도 이용하기 좋아요.', '2024-03-05 15:30:00'),
(5, 11, 5, '농구대 높이도 정확하고 전반적으로 시설이 훌륭합니다.', '2024-03-25 17:45:00');