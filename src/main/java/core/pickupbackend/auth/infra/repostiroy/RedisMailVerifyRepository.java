package core.pickupbackend.auth.infra.repostiroy;

import core.pickupbackend.auth.application.out.MailVerifyRepository;
import core.pickupbackend.auth.provider.VerificationCodeProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Objects;

@Repository
@Profile("prod")
public class RedisMailVerifyRepository implements MailVerifyRepository {

    private static final Logger logger = LoggerFactory.getLogger(RedisMailVerifyRepository.class);
    private static final String KEY_PREFIX = "email:verification:";
    private static final Duration EXPIRATION_TIME = Duration.ofMinutes(30);

    private final StringRedisTemplate redisTemplate;
    private final VerificationCodeProvider verificationCodeProvider;

    public RedisMailVerifyRepository(final StringRedisTemplate redisTemplate, final VerificationCodeProvider verificationCodeProvider) {
        logger.info("RedisMailVerifyRepository initialized");
        this.redisTemplate = Objects.requireNonNull(redisTemplate);
        this.verificationCodeProvider = Objects.requireNonNull(verificationCodeProvider);
    }

    @Override
    public Long issueCode(final String email) {
        final Long verificationCode = verificationCodeProvider.generateVerificationCode();
        final String key = KEY_PREFIX + email;

        redisTemplate.opsForValue().set(key, verificationCode.toString(), EXPIRATION_TIME);

        return verificationCode;
    }

    @Override
    public boolean verify(final String email, final Long verificationCode) {
        String storedCode = redisTemplate.opsForValue().get(KEY_PREFIX + email);

        if (storedCode == null) {
            throw new ApplicationException(ErrorCode.VERIFICATION_KEY_NOT_MATCHED);
        }

        boolean isVerified = storedCode.equals(verificationCode.toString());

        if (isVerified) {
            redisTemplate.delete(KEY_PREFIX + email);
        }

        return isVerified;
    }
}
