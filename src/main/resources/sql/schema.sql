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

CREATE TABLE match (
                         `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                         `title` VARCHAR(255) NOT NULL,
                         `description` VARCHAR(2000) DEFAULT NULL,
                         `court_name` VARCHAR(30) NOT NULL,
                         `location` VARCHAR(100) NOT NULL,
                         `date` DATE NOT NULL,
                         `time` TIME NOT NULL,
                         `level` ENUM('초급', '중급', '상급') NOT NULL,
                         `current_players` TINYINT UNSIGNED NOT NULL DEFAULT 0,
                         `max_players` TINYINT UNSIGNED NOT NULL,
                         `cost` DECIMAL(10, 2) UNSIGNED DEFAULT NULL,
                         `rules` TEXT DEFAULT NULL,
                         `host_id` INT UNSIGNED NOT NULL,
                         `status` ENUM('모집 중', '마감') NOT NULL DEFAULT '모집 중',
                         `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         FOREIGN KEY (`host_id`) REFERENCES `user` (`id`)
)
