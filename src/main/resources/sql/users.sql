CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255), -- 소셜 로그인의 경우 null 가능
                       nickname VARCHAR(50) NOT NULL,
                       profile_image VARCHAR(255),
                       height INT,
                       weight INT,
                       position VARCHAR(20), -- PG, SG, SF, PF, C
                       level VARCHAR(10), -- 초급, 중급, 상급
                       manner_score DECIMAL(3,1) DEFAULT 5.0,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       last_login_at TIMESTAMP,
                       social_provider VARCHAR(20), -- kakao, google 등
                       social_id VARCHAR(100)
);