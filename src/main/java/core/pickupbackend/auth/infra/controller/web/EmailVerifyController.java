package core.pickupbackend.auth.infra.controller.web;

import core.pickupbackend.auth.application.service.EmailService;
import core.pickupbackend.auth.dto.request.EmailIssueRequest;
import core.pickupbackend.auth.dto.request.EmailVerifyRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class EmailVerifyController {

    private static final Logger logger = LoggerFactory.getLogger(EmailVerifyController.class);

    private final EmailService emailService;

    public EmailVerifyController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(summary = "인증 코드 생성 및 발송")
    @PostMapping("/issue-mail")
    @ResponseStatus(HttpStatus.OK)
    public void issueMail(@RequestBody EmailIssueRequest emailIssueRequest) {
        logger.debug("/mail/issue-mail request: {}", emailIssueRequest);
        emailService.sendVerificationCode(emailIssueRequest);
    }

    @Operation(summary = "인증 코드 검증")
    @PostMapping("/verify-mail")
    @ResponseStatus(HttpStatus.OK)
    public boolean verifyMail(@RequestBody EmailVerifyRequest emailVerifyRequest) {
        logger.debug("/mail/verify-mail request: {}", emailVerifyRequest);
        return emailService.verifyEmail(emailVerifyRequest);
    }
}
