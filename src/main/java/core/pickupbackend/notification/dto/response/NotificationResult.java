package core.pickupbackend.notification.dto.response;

public class NotificationResult {
    private final boolean success;
    private final String messageId;

    private NotificationResult(boolean success, String messageId) {
        this.success = success;
        this.messageId = messageId;
    }

    public static NotificationResult success(String messageId) {
        return new NotificationResult(true, messageId);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "NotificationResult{" +
                "success=" + success +
                ", messageId='" + messageId + '\'' +
                '}';
    }
}
