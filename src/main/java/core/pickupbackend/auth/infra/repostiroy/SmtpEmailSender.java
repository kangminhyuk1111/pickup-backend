package core.pickupbackend.auth.infra.repostiroy;

import core.pickupbackend.auth.application.out.EmailSender;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Locale;

@Component
public class SmtpEmailSender implements EmailSender {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public SmtpEmailSender(final JavaMailSender mailSender, final TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendVerificationCode(final String email, final Long verificationCode) {
        try {
            // HTML 이메일 메시지 생성
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(email);
            helper.setSubject("[Pickup] 회원가입 인증 코드");

            // Thymeleaf 컨텍스트 설정
            Context context = new Context(Locale.KOREAN);
            context.setVariable("verificationCode", verificationCode);

            // 템플릿 처리
            String htmlContent = templateEngine.process("mail/verification", context);
            helper.setText(htmlContent, true);

            // 이메일 발송
            mailSender.send(mimeMessage);

        } catch (MailException | MessagingException e) {
            throw new RuntimeException("이메일 전송 실패: " + e.getMessage(), e);
        }
    }
}