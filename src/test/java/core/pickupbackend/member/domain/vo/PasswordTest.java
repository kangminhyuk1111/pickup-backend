package core.pickupbackend.member.domain.vo;

import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ValidateException;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void 일반_생성자로_패스워드_생성() {
        // given
        String rawPassword = "password123";

        // when
        Password password = new Password(rawPassword);

        // then
        assertThat(password.getPassword()).isEqualTo(rawPassword);
    }

    @Test
    void 암호화된_패스워드_생성() {
        // given
        String rawPassword = "password123";

        // when
        Password password = new Password(passwordEncoder, rawPassword);

        // then
        assertThat(password.getPassword()).isNotEqualTo(rawPassword);
        assertThat(passwordEncoder.matches(rawPassword, password.getPassword())).isTrue();
    }

    @Test
    void 패스워드가_null이면_예외발생() {
        // when & then
        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Password(passwordEncoder, null)
        );
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BLANK_EXCEPTION);
    }

    @Test
    void 패스워드가_빈문자열이면_예외발생() {
        // when & then
        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Password(passwordEncoder, "")
        );
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BLANK_EXCEPTION);
    }

    @Test
    void 패스워드가_공백문자이면_예외발생() {
        // when & then
        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Password(passwordEncoder, "   ")
        );
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BLANK_EXCEPTION);
    }

    @Test
    void 패스워드가_8자_미만이면_예외발생() {
        // when & then
        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Password(passwordEncoder, "1234567")
        );
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.PASSWORD_VALID_EXCEPTION);
    }

    @Test
    void 패스워드가_16자_초과하면_예외발생() {
        // when & then
        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Password(passwordEncoder, "12345678901234567")
        );
        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.PASSWORD_VALID_EXCEPTION);
    }

    @Test
    void 패스워드가_8자에서_16자_사이면_정상생성() {
        // given
        String validPassword8 = "12345678";
        String validPassword16 = "1234567890123456";

        // when & then
        assertThat(new Password(passwordEncoder, validPassword8)).isNotNull();
        assertThat(new Password(passwordEncoder, validPassword16)).isNotNull();
    }
}