package core.pickupbackend.auth.dto.request;

public record EmailVerifyRequest(String email, Long verificationCode) {
}
