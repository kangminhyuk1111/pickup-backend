use pickup_db;

CREATE TABLE match_participation (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                user_id BIGINT NOT NULL,
                matching_id BIGINT NOT NULL,
                status VARCHAR(20) NOT NULL DEFAULT 'PENDING',  -- 'PENDING', 'ACCEPTED', 'REJECTED'
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                FOREIGN KEY (matching_id) REFERENCES match(id) ON DELETE CASCADE,
                UNIQUE KEY unique_user_matching (user_id, matching_id)
);

ALTER TABLE match_participation
    ADD COLUMN message TEXT AFTER status;

select * from match_participation;