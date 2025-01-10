package core.pickupbackend.device.domain;

import java.time.LocalDateTime;

public class Device {

    private Long id;
    private Long memberId;
    private String fcmToken;
    private String deviceType;
    private String deviceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public Device() {
    }

    public Device(final Long id, final Long memberId, final String fcmToken, final String deviceType, final String deviceId, final LocalDateTime createdAt, final LocalDateTime updatedAt, final LocalDateTime lastLoginAt) {
        this.id = id;
        this.memberId = memberId;
        this.fcmToken = fcmToken;
        this.deviceType = deviceType;
        this.deviceId = deviceId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLoginAt = lastLoginAt;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }
}
