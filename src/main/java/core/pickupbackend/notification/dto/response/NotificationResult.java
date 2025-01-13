package core.pickupbackend.notification.dto.response;

public class NotificationResult {
    private final boolean success;
    private final String messageId;
    private final String errorMessage;

    private NotificationResult(boolean success, String messageId, String errorMessage) {
        this.success = success;
        this.messageId = messageId;
        this.errorMessage = errorMessage;
    }

    public static NotificationResult success(String messageId) {
        return new NotificationResult(true, messageId, null);
    }

    public static NotificationResult failure(String errorMessage) {
        return new NotificationResult(false, null, errorMessage);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}