package core.pickupbackend.auth.fake;

import core.pickupbackend.auth.provider.VerificationCodeProvider;

public class FakeVerificationCodeProvider implements VerificationCodeProvider {

    private final Long verificationCode;

    public FakeVerificationCodeProvider(final Long verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public Long generateVerificationCode() {
        return this.verificationCode;
    }
}
