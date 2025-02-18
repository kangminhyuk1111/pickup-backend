package core.pickupbackend.auth.provider;

import core.pickupbackend.auth.domain.type.TokenType;
import core.pickupbackend.auth.fake.FakeTokenProvider;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TokenProviderTest {

    private TokenProvider tokenProvider = new FakeTokenProvider();

    @Test
    void 토큰을_생성한다() {
        // given
        String email = "test@example.com";
        String jti = "test-jti";
        Long userId = 1L;

        // when
        String token = tokenProvider.createToken(email, userId, jti, TokenType.ACCESS);

        // then
        assertThat(token).isNotNull();
    }

    @Test
    void 토큰에서_Claims를_추출한다() {
        // given
        String email = "test@example.com";
        String jti = "test-jti";
        Long userId = 1L;
        String token = tokenProvider.createToken(email, userId, jti, TokenType.ACCESS);


        // when
        Claims claims = tokenProvider.extractClaimsFromToken(token);

        // then
        assertThat(claims.getSubject()).isEqualTo(email);
        assertThat(claims.getId()).isEqualTo(jti);
        assertThat(claims.getExpiration()).isAfter(new Date());
    }

    @Test
    void 유효하지_않은_토큰으로_Claims_추출시_예외가_발생한다() {
        // given
        String invalidToken = "invalid-token";

        // when & then
        assertThatThrownBy(() -> tokenProvider.extractClaimsFromToken(invalidToken))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid token");
    }

    @Test
    void 토큰에서_이메일을_추출한다() {
        // given
        String email = "test@example.com";
        Long userId = 1L;
        String token = tokenProvider.createToken(email, userId,"test-jti", TokenType.ACCESS);

        // when
        String extractedEmail = tokenProvider.extractEmailFromToken(token);

        // then
        assertThat(extractedEmail).isEqualTo(email);
    }

    @Test
    void 토큰에서_만료시간을_추출한다() {
        // given
        Long userId = 1L;
        String token = tokenProvider.createToken("test@example.com", userId,"test-jti", TokenType.ACCESS);

        // when
        Date expiration = tokenProvider.extractExpirationTimeFromToken(token);

        // then
        assertThat(expiration).isAfter(new Date());
    }

    @Test
    void 토큰에서_JTI를_추출한다() {
        // given
        String jti = "test-jti";
        Long userId = 1L;
        String token = tokenProvider.createToken("test@example.com", userId, jti, TokenType.ACCESS);

        // when
        String extractedJti = tokenProvider.extractJtiFromToken(token);

        // then
        assertThat(extractedJti).isEqualTo(jti);
    }
}