package core.pickupbackend.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 404 Not Found - 리소스를 찾을 수 없는 경우
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다."),
    NOT_FOUND_NICKNAME(HttpStatus.NOT_FOUND, "존재하지 않는 닉네임입니다."),
    KEY_NOT_INITIALIZED(HttpStatus.NOT_FOUND, "시크릿 키를 찾을 수 없습니다."),
    NOT_FOUND_UPDATE_SET(HttpStatus.NOT_FOUND, "업데이트에 실패하였습니다."),

    // 400 Bad Request - 잘못된 요청
    PASSWORD_NOT_MATCHES(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다."),
    MEMBER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다."),
    EMAIL_PATTERN_EXCEPTION(HttpStatus.BAD_REQUEST, "올바른 이메일 형식이 아닙니다."),
    USERNAME_VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "유저네임은 4에서 16자 사이만 허용합니다."),
    PASSWORD_VALID_EXCEPTION(HttpStatus.BAD_REQUEST, "유효하지 않은 패스워드 형식입니다."),
    FAILED_CREATE_USER(HttpStatus.BAD_REQUEST, "유저 생성을 실패했습니다."),
    BLANK_EXCEPTION(HttpStatus.BAD_REQUEST, "빈칸 없이 입력해주세요."),
    EMAIL_EXIST(HttpStatus.CONFLICT, "이미 등록된 이메일입니다."),
    NICKNAME_EXIST(HttpStatus.CONFLICT, "이미 등록된 닉네임 입니다."),

    // 401 Unauthorized - 인증 실패
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 유저입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "Access Token이 만료되었습니다."),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않거나 형식이 잘못되었습니다."),
    JTI_NOT_FOUND(HttpStatus.UNAUTHORIZED, "jti 토큰을 찾을 수 없습니다."),
    REFRESH_NOT_FOUND(HttpStatus.UNAUTHORIZED, "refresh token을 찾을 수 없습니다.");

    private HttpStatus status;
    private Integer code;
    private String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.code = status.value();
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
