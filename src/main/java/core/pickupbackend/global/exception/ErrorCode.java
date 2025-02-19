package core.pickupbackend.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 404 Not Found - 리소스를 찾을 수 없는 경우
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다."),
    NOT_FOUND_NICKNAME(HttpStatus.NOT_FOUND, "존재하지 않는 닉네임입니다."),
    KEY_NOT_INITIALIZED(HttpStatus.NOT_FOUND, "시크릿 키를 찾을 수 없습니다."),
    NOT_FOUND_UPDATE_SET(HttpStatus.NOT_FOUND, "업데이트에 실패하였습니다."),
    NOT_FOUND_MATCH(HttpStatus.NOT_FOUND, "매치를 찾을 수 없습니다."),

    // 400 Bad Request - 잘못된 요청
    PASSWORD_NOT_MATCHES(HttpStatus.BAD_REQUEST, "비밀번호가 올바르지 않습니다."),
    EMAIL_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 가입된 이메일 입니다."),
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
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "Refresh Token이 만료되었습니다."),
    TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않거나 형식이 잘못되었습니다."),
    JTI_NOT_FOUND(HttpStatus.UNAUTHORIZED, "jti 토큰을 찾을 수 없습니다."),
    REFRESH_NOT_FOUND(HttpStatus.UNAUTHORIZED, "refresh token을 찾을 수 없습니다."),

    // MATCH
    TITLE_BLANK_EXCEPTION(HttpStatus.BAD_REQUEST, "제목은 빈칸이 될 수 없습니다."),
    TITLE_LENGTH_EXCEPTION(HttpStatus.BAD_REQUEST, "제목은 빈칸이 될 수 없습니다."),
    DESCRIPTION_BLANK_EXCEPTION(HttpStatus.BAD_REQUEST, "글 내용은 빈칸이 될 수 없습니다."),
    COURT_BLANK_EXCEPTION(HttpStatus.BAD_REQUEST, "코트 정보는 반드시 입력해야 합니다."),
    LOCATION_BLANK_EXCEPTION(HttpStatus.BAD_REQUEST, "위치 정보는 반드시 입력해야 합니다."),
    DATE_BLANK_EXCEPTION(HttpStatus.BAD_REQUEST, "날짜는 반드시 입력되어야 합니다."),
    DATE_PAST_EXCEPTION(HttpStatus.BAD_REQUEST, "날짜는 과거가 입력될 수 없습니다."),
    PLAYERS_NEGATIVE_EXCEPTION(HttpStatus.BAD_REQUEST, "모집 인원은 반드시 1명 이상이여야 합니다."),
    COAST_NEGATIVE_EXCEPTION(HttpStatus.BAD_REQUEST, "금액은 반드시 입력해야 합니다."),

    // COURT
    COURT_NAME_NOT_NULL(HttpStatus.BAD_REQUEST, "농구장 이름은 필수입니다"),
    COURT_LOCATION_NOT_NULL(HttpStatus.BAD_REQUEST, "지역명은 필수입니다"),
    COURT_ADDRESS_NOT_NULL(HttpStatus.BAD_REQUEST, "주소는 필수입니다"),
    COURT_LATITUDE_NOT_NULL(HttpStatus.BAD_REQUEST, "위도는 필수입니다"),
    COURT_LONGITUDE_NOT_NULL(HttpStatus.BAD_REQUEST, "경도는 필수입니다"),
    COURT_HOOPS_NOT_NULL(HttpStatus.BAD_REQUEST, "골대 개수는 필수입니다"),
    COURT_SURFACE_NOT_NULL(HttpStatus.BAD_REQUEST, "바닥 재질은 필수입니다"),
    COURT_LIGHTING_NOT_NULL(HttpStatus.BAD_REQUEST, "조명 여부는 필수입니다"),
    COURT_PARKING_NOT_NULL(HttpStatus.BAD_REQUEST, "주차 가능 여부는 필수입니다"),
    COURT_RATING_NOT_NULL(HttpStatus.BAD_REQUEST, "평점은 필수입니다"),
    COURT_LATITUDE_RANGE(HttpStatus.BAD_REQUEST, "위도는 -90.000000에서 90.000000 사이여야 합니다"),
    COURT_LONGITUDE_RANGE(HttpStatus.BAD_REQUEST, "경도는 -180.000000에서 180.000000 사이여야 합니다"),
    COURT_HOOPS_RANGE(HttpStatus.BAD_REQUEST, "골대 개수는 0보다 커야 합니다"),
    COURT_RATING_RANGE(HttpStatus.BAD_REQUEST, "평점은 0.000000에서 5.000000 사이여야 합니다"),
    COURT_NOT_FOUND(HttpStatus.NOT_FOUND, "코트 정보를 찾을 수 없습니다."),

    // Device
    DEVICE_NOT_FOUND(HttpStatus.NOT_FOUND, "디바이스 정보를 찾을 수 없습니다."),
    MESSAGE_NOT_PUSHED(HttpStatus.INTERNAL_SERVER_ERROR, "메세지가 정상적으로 전송되지 못했습니다."),

    // enum
    NOT_FOUND_STATUS(HttpStatus.NOT_FOUND, "상태값이 올바르지 않습니다."),
    CREATOR_CAN_NOT_CREATE_PARTICIPATION(HttpStatus.BAD_REQUEST, "작성자는 매칭 신청이 불가능 합니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "빈값은 입력할 수 없습니다."),

    // mail
    VERIFICATION_NOT_MATCHED(HttpStatus.BAD_REQUEST, "인증 코드가 유효하지 않습니다."),
    VERIFICATION_KEY_NOT_MATCHED(HttpStatus.BAD_REQUEST, "잘못된 이메일 인증 요청입니다.");


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
