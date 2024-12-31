package core.pickupbackend.auth.fake;

import core.pickupbackend.auth.provider.JtiProvider;

public class FakeJtiProvider implements JtiProvider {

    private static final String FAKE_JTI_PREFIX = "fake-jti-";
    private int sequence = 0;

    @Override
    public String generateJti() {
        return FAKE_JTI_PREFIX + (++sequence);
    }
}