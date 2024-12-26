package core.pickupbackend.global.common;

import core.pickupbackend.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String message;
    private Integer code;
    private HttpStatus status;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
    }

    public ErrorResponse(String message, Integer code, HttpStatus status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }
}
