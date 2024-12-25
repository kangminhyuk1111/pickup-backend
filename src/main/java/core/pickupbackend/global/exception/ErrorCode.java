package core.pickupbackend.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    PASSWORD_NOT_MATCHES(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다."),
    MEMBER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 유저입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.FORBIDDEN, "Access Token이 만료되었습니다."),
    KEY_NOT_INITIALIZED(HttpStatus.NOT_FOUND, "시크릿 키를 찾을 수 없습니다."),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "토큰이 존재하지 않거나 형식이 잘못되었습니다."),
    JTI_NOT_FOUND(HttpStatus.NOT_FOUND, "jti 토큰을 찾을 수 없습니다."),
    REFRESH_NOT_FOUND(HttpStatus.NOT_FOUND, "refresh token을 찾을 수 없습니다."),
    NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다."),
    NOT_FOUND_NICKNAME(HttpStatus.NOT_FOUND, "존재하지 않는 닉네임입니다."),

    USERNAME_VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "유저네임은 4에서 16자 사이만 허용합니다."),
    EMAIL_VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 이메일 형식입니다."),
    PASSWORD_VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 패스워드 형식입니다.");

    private HttpStatus status;
    private String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
