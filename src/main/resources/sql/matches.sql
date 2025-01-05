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
