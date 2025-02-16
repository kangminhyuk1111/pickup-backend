package core.pickupbackend.auth.dto.request;

public record SaveAuthCredentialCommand(
    String jti,
    String accessToken,
    String refreshToken
) {}