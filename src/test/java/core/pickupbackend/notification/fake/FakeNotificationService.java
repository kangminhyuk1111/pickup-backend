package core.pickupbackend.notification.fake;

import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.MessagePushException;
import core.pickupbackend.notification.dto.reqeust.NotificationRequestDto;
import core.pickupbackend.notification.dto.response.NotificationResult;
import core.pickupbackend.notification.service.NotificationService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeNotificationService implements NotificationService {
    private final List<NotificationRecord> sentNotifications = new ArrayList<>();
    private boolean shouldFail = false;

    @Override
    public NotificationResult send(NotificationRequestDto<String> request) {
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
    public List<NotificationResult> sendMultiCast(NotificationRequestDto<List<String>> request) {
        if (shouldFail) {
            throw new MessagePushException(ErrorCode.MESSAGE_NOT_PUSHED);
        }

        return request.getTargetToken().stream()
                .map(token -> send(new NotificationRequestDto<>(
                        request.getTitle(),
                        request.getBody(),
                        token
                )))
                .toList();
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