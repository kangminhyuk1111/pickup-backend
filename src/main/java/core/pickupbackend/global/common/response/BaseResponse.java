package core.pickupbackend.global.common.response;

import core.pickupbackend.global.common.code.StatusCode;

public class BaseResponse<T> {

    private Integer status;
    private String message;
    private T data;

    public BaseResponse() {
    }

    public BaseResponse(StatusCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.data = null;
    }

    public BaseResponse(final StatusCode code, final T data) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.data = data;
    }

    public BaseResponse(final Integer status, final String message, final T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
