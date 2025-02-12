package core.pickupbackend.device.dto.response;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.domain.type.DeviceType;

import java.time.LocalDateTime;

public record DeviceResponse(
        Long id,
        Long memberId,
        String fcmToken,
        DeviceType deviceType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime lastLoginAt
) {
    public static DeviceResponse from(Device device) {
        return new DeviceResponse(
                device.getId(),
                device.getMemberId(),
                device.getFcmToken(),
                device.getDeviceType(),
                device.getCreatedAt(),
                device.getUpdatedAt(),
                device.getLastLoginAt()
        );
    }
}