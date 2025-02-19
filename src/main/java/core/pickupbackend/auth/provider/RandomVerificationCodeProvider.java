package core.pickupbackend.auth.provider;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class RandomVerificationCodeProvider implements VerificationCodeProvider {

    private static final Random RANDOM = new SecureRandom();
    private static final int MIN_CODE = 100000;
    private static final int CODE_RANGE = 999999;

    @Override
    public Long generateVerificationCode() {
        return (long) (MIN_CODE + RANDOM.nextInt(CODE_RANGE));
    }
}
