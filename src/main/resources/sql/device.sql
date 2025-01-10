use pickup_db;

CREATE TABLE device (
                        id BIGINT AUTO_INCREMENT,
                        member_id BIGINT NOT NULL,
                        fcm_token VARCHAR(255) NOT NULL,
                        device_type VARCHAR(50) NOT NULL,
                        device_id VARCHAR(255) NOT NULL,
                        created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        last_login_at DATETIME,
                        PRIMARY KEY (id),
                        INDEX idx_member_id (member_id),
                        INDEX idx_device_id (device_id),
                        CONSTRAINT fk_device_member FOREIGN KEY (member_id)
                            REFERENCES users (id) ON DELETE CASCADE
);