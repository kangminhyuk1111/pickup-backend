package core.pickupbackend.global.common.code;

import org.springframework.http.HttpStatus;

public enum StatusCode {

    SUCCESS(HttpStatus.OK);

    private final Integer status;
    private final String message;

    StatusCode(final HttpStatus status) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
