package core.pickupbackend.device.dto.request;

public class DeleteDeviceRequestDto {

    private String fcmToken;

    public DeleteDeviceRequestDto(final String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getFcmToken() {
        return fcmToken;
    }
}
