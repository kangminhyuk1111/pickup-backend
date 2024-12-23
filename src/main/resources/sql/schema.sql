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
-- 농구장 테이블
CREATE TABLE courts (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        location VARCHAR(50) NOT NULL, -- 구 단위
                        address VARCHAR(255) NOT NULL,
                        latitude DECIMAL(10,8) NOT NULL,
                        longitude DECIMAL(11,8) NOT NULL,
                        hoops INT NOT NULL,
                        surface VARCHAR(20) NOT NULL, -- 아스팔트, 고무 등
                        lighting BOOLEAN DEFAULT false,
                        parking BOOLEAN DEFAULT false,
                        rating DECIMAL(2,1) DEFAULT 0.0,
                        best_time VARCHAR(20),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
-- 코트 이미지 테이블
CREATE TABLE court_images (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              court_id BIGINT NOT NULL,
                              image_url VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (court_id) REFERENCES courts(id)
);
-- 코트 시설 테이블
CREATE TABLE court_facilities (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  court_id BIGINT NOT NULL,
                                  facility VARCHAR(50) NOT NULL, -- 화장실, 편의점, 벤치 등
                                  FOREIGN KEY (court_id) REFERENCES courts(id)
);
-- 인기 시간대 테이블
CREATE TABLE popular_times (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               court_id BIGINT NOT NULL,
                               day_of_week VARCHAR(20) NOT NULL,
                               time_range VARCHAR(50) NOT NULL,
                               FOREIGN KEY (court_id) REFERENCES courts(id)
);
-- 게임(매칭) 테이블
CREATE TABLE games (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       court_id BIGINT NOT NULL,
                       host_id BIGINT NOT NULL,
                       game_date DATE NOT NULL,
                       start_time TIME NOT NULL,
                       end_time TIME NOT NULL,
                       max_players INT NOT NULL,
                       current_players INT DEFAULT 1,
                       level VARCHAR(10), -- 초급, 중급, 상급
                       status VARCHAR(20) DEFAULT 'RECRUITING', -- RECRUITING, FULL, COMPLETED
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (court_id) REFERENCES courts(id),
                       FOREIGN KEY (host_id) REFERENCES users(id)
);
-- 게임 참가자 테이블
CREATE TABLE game_participants (
                                   id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                   game_id BIGINT NOT NULL,
                                   user_id BIGINT NOT NULL,
                                   status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, APPROVED, REJECTED
                                   message TEXT,
                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                   FOREIGN KEY (game_id) REFERENCES games(id),
                                   FOREIGN KEY (user_id) REFERENCES users(id)
);
-- 코트 리뷰 테이블
CREATE TABLE court_reviews (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               court_id BIGINT NOT NULL,
                               user_id BIGINT NOT NULL,
                               rating DECIMAL(2,1) NOT NULL,
                               content TEXT,
                               created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               FOREIGN KEY (court_id) REFERENCES courts(id),
                               FOREIGN KEY (user_id) REFERENCES users(id)
);
-- 매너 평가 테이블
CREATE TABLE manner_evaluations (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    evaluator_id BIGINT NOT NULL,
                                    target_id BIGINT NOT NULL,
                                    game_id BIGINT NOT NULL,
                                    score DECIMAL(2,1) NOT NULL,
                                    comment TEXT,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    FOREIGN KEY (evaluator_id) REFERENCES users(id),
                                    FOREIGN KEY (target_id) REFERENCES users(id),
                                    FOREIGN KEY (game_id) REFERENCES games(id)
);