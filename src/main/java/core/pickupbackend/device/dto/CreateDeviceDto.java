package core.pickupbackend.device.dto;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.domain.type.DeviceType;

public class CreateDeviceDto {

    private String fcmToken;
    private DeviceType deviceType;

    public CreateDeviceDto(String fcmToken, DeviceType deviceType) {
        this.fcmToken = fcmToken;
        this.deviceType = deviceType;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public Device toEntity() {
        return new Device(fcmToken, deviceType);
    }
}
