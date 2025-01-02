package core.pickupbackend.global.exception;

public class MatchException extends ApplicationException {
    public MatchException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
