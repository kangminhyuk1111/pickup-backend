package core.pickupbackend.auth.dto.response;

import core.pickupbackend.auth.domain.AuthCredential;

public record LoginResponse(String jti, String accessToken, String refreshToken) {
    public static LoginResponse from(AuthCredential credential) {
        return new LoginResponse(
                credential.jti(),
                credential.accessToken(),
                credential.refreshToken()
        );
    }
}
