package core.pickupbackend.auth.application.service;

import core.pickupbackend.auth.application.in.EmailVerifyUseCase;
import core.pickupbackend.auth.application.out.EmailSender;
import core.pickupbackend.auth.application.out.MailVerifyRepository;
import core.pickupbackend.auth.dto.request.EmailIssueRequest;
import core.pickupbackend.auth.dto.request.EmailVerifyRequest;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailVerifyUseCase {

    private final MailVerifyRepository mailVerifyRepository;
    private final EmailSender emailSender;

    public EmailService(final MailVerifyRepository mailVerifyRepository, final EmailSender emailSender) {
        this.mailVerifyRepository = mailVerifyRepository;
        this.emailSender = emailSender;
    }

    @Async
    @Override
    public void sendVerificationCode(final EmailIssueRequest emailIssueRequest) {
        final Long verificationCode = mailVerifyRepository.issueCode(emailIssueRequest.email());

        emailSender.sendVerificationCode(emailIssueRequest.email(), verificationCode);
    }

    @Override
    public boolean verifyEmail(final EmailVerifyRequest emailVerifyRequest) {
        if (!mailVerifyRepository.verify(emailVerifyRequest.email(), emailVerifyRequest.verificationCode())) {
            throw new ApplicationException(ErrorCode.VERIFICATION_NOT_MATCHED);
        }

        return true;
    }
}
