package core.pickupbackend.auth.domain;

public class AuthCredential {

    private final String jti;
    private final String accessToken;
    private final String refreshToken;

    public AuthCredential(final String jti, final String accessToken, final String refreshToken) {
        this.jti = jti;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getJti() {
        return jti;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
