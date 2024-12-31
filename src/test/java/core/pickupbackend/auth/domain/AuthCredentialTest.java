package core.pickupbackend.auth.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthCredentialTest {

    @Test
    void 인증_자격증명_객체_생성_및_getter_테스트() {
        // given
        String jti = "test-jti-123";
        String accessToken = "test-access-token";
        String refreshToken = "test-refresh-token";

        // when
        AuthCredential credential = new AuthCredential(jti, accessToken, refreshToken);

        // then
        assertThat(credential.jti()).isEqualTo(jti);
        assertThat(credential.accessToken()).isEqualTo(accessToken);
        assertThat(credential.refreshToken()).isEqualTo(refreshToken);
    }
}