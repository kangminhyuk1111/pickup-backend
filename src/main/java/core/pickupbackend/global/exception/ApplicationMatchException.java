package core.pickupbackend.global.exception;

public class ApplicationMatchException extends ApplicationException {
    public ApplicationMatchException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
