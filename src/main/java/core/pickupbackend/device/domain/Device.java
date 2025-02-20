package core.pickupbackend.device.domain;

import core.pickupbackend.device.domain.type.DeviceType;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Device {

    private static final Pattern FCM_TOKEN_PATTERN =
            Pattern.compile("^[a-zA-Z0-9:_-]{140,200}$");

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
        validateFcmToken(fcmToken);
        this.fcmToken = fcmToken;
        this.deviceType = deviceType;
    }

    public Device(final Long id, final Long memberId, final String fcmToken, final DeviceType deviceType, final LocalDateTime createdAt, final LocalDateTime updatedAt, final LocalDateTime lastLoginAt) {
        validateFcmToken(fcmToken);
        this.id = id;
        this.memberId = memberId;
        this.fcmToken = fcmToken;
        this.deviceType = deviceType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLoginAt = lastLoginAt;
    }

    private void validateFcmToken(final String fcmToken) {
        if (fcmToken == null || fcmToken.isBlank()) {
            throw new ApplicationException(ErrorCode.FCM_TOKEN_NOT_BLANK);
        }

        if (!FCM_TOKEN_PATTERN.matcher(fcmToken).matches()) {
            throw new ApplicationException(ErrorCode.FCM_TOKEN_NOT_VALID);
        }
    }

    public Device updateMemberId(final Long memberId) {
        return new Device(id, memberId, fcmToken, deviceType, createdAt, updatedAt, lastLoginAt);
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
