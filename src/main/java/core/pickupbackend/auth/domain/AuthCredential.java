package core.pickupbackend.auth.domain;

public record AuthCredential(String jti, String accessToken, String refreshToken) {
}
