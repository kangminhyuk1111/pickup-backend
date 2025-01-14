use pickup_db;

CREATE TABLE device (
                        id BIGINT AUTO_INCREMENT,
                        member_id BIGINT NOT NULL,
                        fcm_token VARCHAR(255) NOT NULL,
                        device_type VARCHAR(50) NOT NULL CHECK (device_type IN ('IOS', 'ANDROID')),
                        created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        last_login_at DATETIME,
                        PRIMARY KEY (id),
                        UNIQUE KEY uk_fcm_token (fcm_token),
                        INDEX idx_member_id (member_id),
                        CONSTRAINT fk_device_member FOREIGN KEY (member_id)
                            REFERENCES users (id) ON DELETE CASCADE
);

ALTER TABLE device
    MODIFY COLUMN member_id BIGINT NOT NULL DEFAULT 0;

ALTER TABLE device
    DROP FOREIGN KEY fk_device_member;

truncate device;

ALTER TABLE device
    ADD CONSTRAINT uk_fcm_token UNIQUE (fcm_token);