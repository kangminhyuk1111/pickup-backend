package core.pickupbackend.auth.application.out;

public interface MailVerifyRepository {
    Long issueCode(String email);
    boolean verify(String email, Long verificationCode);
}
