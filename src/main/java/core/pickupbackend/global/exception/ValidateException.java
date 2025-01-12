package core.pickupbackend.global.exception;

public class ValidateException extends ApplicationException{

    public ValidateException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
