package core.pickupbackend.device.dto.request;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.domain.type.DeviceType;

public record CreateDeviceDto(String fcmToken, Long memberId, DeviceType deviceType, boolean status) {
    public Device toEntity() {
        return new Device(fcmToken, memberId, deviceType, status);
    }
}
