package core.pickupbackend.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordServiceTest {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PasswordService passwordService = new PasswordService(passwordEncoder);

    @Test
    void 비밀번호가_일치하면_true를_반환한다() {
        // given
        String rawPassword = "password123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // when
        boolean result = passwordService.matches(rawPassword, encodedPassword);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void 비밀번호가_일치하지_않으면_false를_반환한다() {
        // given
        String rawPassword = "password123";
        String wrongPassword = "wrongPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // when
        boolean result = passwordService.matches(wrongPassword, encodedPassword);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void PasswordService_인스턴스가_정상적으로_생성된다() {
        // when
        PasswordService service = new PasswordService(passwordEncoder);

        // then
        assertThat(service).isNotNull();
    }
}