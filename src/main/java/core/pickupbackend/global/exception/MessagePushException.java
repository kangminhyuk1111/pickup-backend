package core.pickupbackend.global.exception;

public class MessagePushException extends ApplicationException{
    public MessagePushException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
