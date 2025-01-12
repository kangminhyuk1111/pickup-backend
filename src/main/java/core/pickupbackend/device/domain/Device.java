package core.pickupbackend.device.domain;

import core.pickupbackend.device.domain.type.DeviceType;

import java.time.LocalDateTime;

public class Device {

    private Long id;
    private Long memberId;
    private String fcmToken;
    private DeviceType deviceType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;

    public Device() {
    }

    public Device(final String fcmToken, final DeviceType deviceType) {
        this.fcmToken = fcmToken;
        this.deviceType = deviceType;
    }

    public Device(final Long id, final Long memberId, final String fcmToken, final DeviceType deviceType, final LocalDateTime createdAt, final LocalDateTime updatedAt, final LocalDateTime lastLoginAt) {
        this.id = id;
        this.memberId = memberId;
        this.fcmToken = fcmToken;
        this.deviceType = deviceType;
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

    public DeviceType getDeviceType() {
        return deviceType;
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
