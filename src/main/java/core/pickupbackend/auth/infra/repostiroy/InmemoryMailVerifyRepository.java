package core.pickupbackend.auth.infra.repostiroy;

import core.pickupbackend.auth.application.out.MailVerifyRepository;
import core.pickupbackend.auth.provider.VerificationCodeProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("local")
public class InmemoryMailVerifyRepository implements MailVerifyRepository {

    private static final Logger logger = LoggerFactory.getLogger(InmemoryMailVerifyRepository.class);

    private final Map<String, Long> verifications = new ConcurrentHashMap<>();

    private final VerificationCodeProvider verificationCodeProvider;

    public InmemoryMailVerifyRepository(final VerificationCodeProvider verificationCodeProvider) {
        logger.info("InmemoryMailVerifyRepository initialized");
        this.verificationCodeProvider = verificationCodeProvider;
    }

    @Override
    public Long issueCode(final String email) {
        final Long verificationCode = verificationCodeProvider.generateVerificationCode();

        verifications.put(email, verificationCode);

        return verificationCode;
    }

    @Override
    public boolean verify(final String email, final Long verificationCode) {
        if (!verifications.containsKey(email)) {
            throw new ApplicationException(ErrorCode.VERIFICATION_KEY_NOT_MATCHED);
        }

        return verifications.get(email).equals(verificationCode);
    }
}
