use pickup_db;

SET FOREIGN_KEY_CHECKS = 0;

-- 테이블 비우기
TRUNCATE TABLE match_participation;
TRUNCATE TABLE `match`;

-- 테이블 구조 변경
-- 기존 location 컬럼 제거
ALTER TABLE `match` DROP COLUMN `location`;

-- district_id와 location_detail 컬럼 추가
ALTER TABLE `match`
    ADD COLUMN `district_id` INT AFTER `court_name`,
    ADD COLUMN `location_detail` VARCHAR(200) AFTER `district_id`;

-- 외래키 제약조건 추가
ALTER TABLE `match`
    ADD CONSTRAINT `fk_match_district`
    FOREIGN KEY (`district_id`) REFERENCES `districts`(`id`);

-- 외래키 제약조건 체크 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;

-- 테이블 구조 확인
DESCRIBE `match`;

ALTER TABLE `match`
DROP FOREIGN KEY `match_ibfk_1`,
    ADD CONSTRAINT `fk_match_host_cascade`  -- 새로운 이름으로 변경
        FOREIGN KEY (`host_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE;

ALTER TABLE `court_reviews`
DROP FOREIGN KEY `fk_review_user`,
    ADD CONSTRAINT `fk_review_user_cascade`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE;