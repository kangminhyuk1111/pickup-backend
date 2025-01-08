package core.pickupbackend.global.common.response;

import core.pickupbackend.global.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private String message;
    private Integer status;

    public ErrorResponse(ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getCode();
    }

    public ErrorResponse(String message, Integer code, HttpStatus status) {
        this.message = message;
        this.status = code;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }
}
