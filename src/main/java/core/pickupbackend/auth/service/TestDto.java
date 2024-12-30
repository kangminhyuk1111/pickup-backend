package core.pickupbackend.auth.service;

public class TestDto {

    private final String accessToken;

    public TestDto(final String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
