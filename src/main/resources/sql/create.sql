create schema pickup_db;

use pickup_db;

CREATE TABLE `court` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `name` varchar(255) NOT NULL,
    `location` varchar(255) NOT NULL,
    `address` varchar(255) NOT NULL,
    `latitude` double NOT NULL,
    `longitude` double NOT NULL,
    `hoops` int NOT NULL,
    `surface` varchar(50) NOT NULL,
    `lighting` tinyint(1) NOT NULL,
    `parking` tinyint(1) NOT NULL,
    `rating` double NOT NULL,
    `best_time` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `court_facilities` (
                                    `court_id` bigint DEFAULT NULL,
                                    `facility` varchar(50) DEFAULT NULL,
    KEY `court_id` (`court_id`),
    CONSTRAINT `court_facilities_ibfk_1` FOREIGN KEY (`court_id`) REFERENCES `court` (`id`))
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `court_images` (
                                `court_id` bigint DEFAULT NULL,
                                `image_url` varchar(255) DEFAULT NULL,
    KEY `court_id` (`court_id`),
    CONSTRAINT `court_images_ibfk_1` FOREIGN KEY (`court_id`) REFERENCES `court` (`id`))
    ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `email` varchar(100) NOT NULL,
    `password` varchar(255) DEFAULT NULL,
    `nickname` varchar(50) NOT NULL,
    `profile_image` varchar(255) DEFAULT NULL,
    `height` int DEFAULT NULL,
    `weight` int DEFAULT NULL,
    `position` varchar(20) DEFAULT NULL,
    `level` varchar(20) DEFAULT NULL,
    `manner_score` decimal(3,1) DEFAULT '5.0',
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `last_login_at` timestamp NULL DEFAULT NULL,
    `social_provider` varchar(20) DEFAULT NULL,
    `social_id` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email` (`email`))
    ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `device` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `member_id` bigint NOT NULL DEFAULT '0',
                          `fcm_token` varchar(255) NOT NULL,
    `device_type` varchar(50) NOT NULL,
    `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `last_login_at` datetime DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_fcm_token` (`fcm_token`),
    KEY `idx_member_id` (`member_id`))
    ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `court_reviews` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `court_id` bigint NOT NULL,
                                 `user_id` bigint NOT NULL,
                                 `rating` tinyint NOT NULL,
                                 `content` text NOT NULL,
                                 `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 `is_deleted` tinyint(1) DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY `idx_court_id` (`court_id`),
    KEY `idx_user_id` (`user_id`),
    CONSTRAINT `fk_review_court` FOREIGN KEY (`court_id`) REFERENCES `court` (`id`),
    CONSTRAINT `fk_review_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    CONSTRAINT `rating_range` CHECK (((`rating` >= 1) and (`rating` <= 5))))
    ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `match` (
                         `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                         `title` varchar(255) NOT NULL,
    `description` varchar(2000) DEFAULT NULL,
    `court_name` varchar(30) NOT NULL,
    `location` varchar(100) NOT NULL,
    `date` date NOT NULL,
    `time` time NOT NULL,
    `level` enum('BEGINNER','INTERMEDIATE','ADVANCED') NOT NULL,
    `current_players` int unsigned NOT NULL,
    `max_players` int unsigned NOT NULL,
    `cost` bigint unsigned NOT NULL,
    `rules` varchar(2000) DEFAULT NULL,
    `host_id` bigint NOT NULL,
    `status` enum('OPEN','CLOSED') NOT NULL DEFAULT 'OPEN',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `host_id` (`host_id`),
    CONSTRAINT `match_ibfk_1` FOREIGN KEY (`host_id`) REFERENCES `users` (`id`))
    ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `match_participation` (
                                       `id` bigint unsigned NOT NULL AUTO_INCREMENT,
                                       `user_id` bigint NOT NULL,
                                       `matching_id` bigint unsigned NOT NULL,
                                       `status` varchar(20) NOT NULL DEFAULT 'PENDING',
    `message` text,
    `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_user_matching` (`user_id`,`matching_id`),
    KEY `matching_id` (`matching_id`),
    CONSTRAINT `match_participation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
    CONSTRAINT `match_participation_ibfk_2` FOREIGN KEY (`matching_id`) REFERENCES `match` (`id`) ON DELETE CASCADE)
    ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;