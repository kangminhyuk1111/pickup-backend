package core.pickupbackend.auth.provider;

import core.pickupbackend.auth.fake.FakeJtiProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FakeJtiProviderTest {

    private FakeJtiProvider jtiProvider = new FakeJtiProvider();

    @Test
    void JTI를_생성한다() {
        // when
        String jti = jtiProvider.generateJti();

        // then
        assertThat(jti).isNotNull();
        assertThat(jti).startsWith("fake-jti-");
    }

    @Test
    void 생성된_JTI는_순차적으로_증가한다() {
        // when
        String firstJti = jtiProvider.generateJti();
        String secondJti = jtiProvider.generateJti();

        // then
        assertThat(firstJti).isEqualTo("fake-jti-1");
        assertThat(secondJti).isEqualTo("fake-jti-2");
    }
}