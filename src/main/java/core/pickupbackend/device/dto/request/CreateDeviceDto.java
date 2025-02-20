package core.pickupbackend.device.dto.request;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.domain.type.DeviceType;

public class CreateDeviceDto {

    private String fcmToken;
    private Long memberId;
    private DeviceType deviceType;

    public CreateDeviceDto(String fcmToken, Long memberId, DeviceType deviceType) {
        this.fcmToken = fcmToken;
        this.memberId = memberId;
        this.deviceType = deviceType;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public Long getMemberId() {
        return memberId;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public Device toEntity() {
        return new Device(fcmToken, memberId, deviceType);
    }
}
