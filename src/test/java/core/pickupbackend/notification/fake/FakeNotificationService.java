package core.pickupbackend.notification.fake;

import com.google.firebase.messaging.FirebaseMessagingException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.MessagePushException;
import core.pickupbackend.notification.dto.reqeust.GeneralNoticeCommand;
import core.pickupbackend.notification.dto.reqeust.NotificationCommand;
import core.pickupbackend.notification.dto.response.NotificationResult;
import core.pickupbackend.notification.application.port.in.NotificationPort;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeNotificationService implements NotificationPort {
    private final List<NotificationRecord> sentNotifications = new ArrayList<>();
    private boolean shouldFail = false;

    @Override
    public NotificationResult send(NotificationCommand<String> request) {
        if (shouldFail) {
            throw new MessagePushException(ErrorCode.MESSAGE_NOT_PUSHED);
        }

        String messageId = generateMessageId();
        sentNotifications.add(new NotificationRecord(
                request.getTitle(),
                request.getBody(),
                request.getTargetToken(),
                messageId
        ));

        return NotificationResult.success(messageId);
    }

    @Override
    public List<NotificationResult> sendMultiCast(NotificationCommand<List<String>> request) {
        if (shouldFail) {
            throw new MessagePushException(ErrorCode.MESSAGE_NOT_PUSHED);
        }

        return request.getTargetToken().stream()
                .map(token -> send(new NotificationCommand<>(
                        request.getTitle(),
                        request.getBody(),
                        token
                )))
                .toList();
    }

    @Override
    public List<NotificationResult> sendAll(final GeneralNoticeCommand generalNoticeRequestDto) throws FirebaseMessagingException {
        return List.of();
    }

    public List<NotificationRecord> getSentNotifications() {
        return new ArrayList<>(sentNotifications);
    }

    public void setShouldFail(boolean shouldFail) {
        this.shouldFail = shouldFail;
    }

    private String generateMessageId() {
        return "fake-message-" + UUID.randomUUID();
    }

    // 알림 기록을 위한 레코드
    public record NotificationRecord(
            String title,
            String body,
            String token,
            String messageId
    ) {
    }
}