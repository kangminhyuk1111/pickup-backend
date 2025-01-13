package core.pickupbackend.notification.service;

import com.google.firebase.messaging.*;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.MessagePushException;
import core.pickupbackend.notification.dto.reqeust.NotificationRequestDto;
import core.pickupbackend.notification.dto.response.NotificationResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FcmNotificationService implements NotificationService {
    private final FirebaseMessaging firebaseMessaging;

    public FcmNotificationService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    @Override
    public NotificationResult send(final NotificationRequestDto<String> request) throws FirebaseMessagingException {
        final Notification notification = createNotification(request);
        final Message message = Message.builder()
                .setNotification(notification)
                .setToken(request.getTargetToken())
                .build();

        final String messageId = firebaseMessaging.send(message);
        return NotificationResult.success(messageId);
    }

    @Override
    public List<NotificationResult> sendMultiCast(final NotificationRequestDto<List<String>> request) throws FirebaseMessagingException {
        final MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(request.getTargetToken())
                .setNotification(createNotification(request))
                .build();

        final BatchResponse response = firebaseMessaging.sendMulticast(message);

        if (response.getFailureCount() > 0) {
            throw new MessagePushException(ErrorCode.MESSAGE_NOT_PUSHED);
        }

        return response.getResponses().stream()
                .map(sendResponse -> NotificationResult.success(sendResponse.getMessageId()))
                .toList();
    }

    private Notification createNotification(final NotificationRequestDto<?> request) {
        return Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .build();
    }
}
