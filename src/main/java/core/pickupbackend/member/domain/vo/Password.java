package core.pickupbackend.member.domain.vo;

import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ValidateException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {

    private static final String PASSWORD_REGEX = "^.{8,16}$";

    private String password;

    public Password(final String password) {
        this.password = password;
    }

    public Password(final PasswordEncoder passwordEncoder, final String password) {
        validatePassword(password);
        this.password = passwordEncoder.encode(password);
    }

    private void validatePassword(final String password) {
        if (password == null || password.isBlank()) {
            throw new ValidateException(ErrorCode.BLANK_EXCEPTION);
        }

        if (!password.matches(PASSWORD_REGEX)) {
            throw new ValidateException(ErrorCode.PASSWORD_VALID_EXCEPTION);
        }
    }

    public String getPassword() {
        return password;
    }
}
