package core.pickupbackend.device.domain.type;

public enum DeviceType {

    IOS("iOS"),
    ANDROID("Android");

    private String deviceType;

    DeviceType(final String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
