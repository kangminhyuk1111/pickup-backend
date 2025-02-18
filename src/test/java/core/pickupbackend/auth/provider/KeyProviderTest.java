package core.pickupbackend.auth.provider;

import core.pickupbackend.auth.fake.FakeKeyProvider;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class KeyProviderTest {
    private FakeKeyProvider keyProvider = new FakeKeyProvider();

    @Test
    void SecretKey를_생성한다() {
        // when
        SecretKey secretKey = keyProvider.getSecretKey();

        // then
        assertThat(secretKey).isNotNull();
        assertThat(secretKey.getAlgorithm()).isEqualTo("HmacSHA256");
    }
}
