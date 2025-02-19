package core.pickupbackend.auth.application.out;

public interface EmailSender {
    void sendVerificationCode(String email, Long verificationCode);
}
