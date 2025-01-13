package core.pickupbackend.device.dto;

public class DeleteDeviceDto {

    private String fcmToken;

    public DeleteDeviceDto(final String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getFcmToken() {
        return fcmToken;
    }
}
