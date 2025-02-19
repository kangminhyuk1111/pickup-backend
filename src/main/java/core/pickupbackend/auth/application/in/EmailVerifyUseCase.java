package core.pickupbackend.auth.application.in;

import core.pickupbackend.auth.dto.request.EmailIssueRequest;
import core.pickupbackend.auth.dto.request.EmailVerifyRequest;

public interface EmailVerifyUseCase {
    void sendVerificationCode(EmailIssueRequest emailVerifyRequest);
    boolean verifyEmail(EmailVerifyRequest emailVerifyRequest);
}
