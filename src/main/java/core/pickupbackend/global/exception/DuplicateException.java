package core.pickupbackend.global.exception;

public class DuplicateException extends ApplicationException{

    public DuplicateException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
