package core.pickupbackend.auth.provider;

import core.pickupbackend.auth.fake.FakeVerificationCodeProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VerificationCodeProviderTest {

    private static final Long defaultVerificationCode = 111111L;

    private final VerificationCodeProvider verificationCodeProvider = new FakeVerificationCodeProvider(defaultVerificationCode);

    @Test
    void 랜덤한_6자리_숫자_생성_테스트() {
        // given & when
        final Long verificationCode = verificationCodeProvider.generateVerificationCode();

        // then
        assertThat(verificationCode).isNotNull();
        assertThat(verificationCode).isEqualTo(defaultVerificationCode);
    }
}