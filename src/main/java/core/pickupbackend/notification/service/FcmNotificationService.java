package core.pickupbackend.notification.service;

import com.google.firebase.messaging.*;
import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.service.DeviceService;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.MessagePushException;
import core.pickupbackend.notification.dto.reqeust.GeneralNoticeRequestDto;
import core.pickupbackend.notification.dto.reqeust.NotificationRequestDto;
import core.pickupbackend.notification.dto.response.NotificationResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FcmNotificationService implements NotificationService {
    private final FirebaseMessaging firebaseMessaging;
    private final DeviceService deviceService;

    public FcmNotificationService(FirebaseMessaging firebaseMessaging, final DeviceService deviceService) {
        this.firebaseMessaging = firebaseMessaging;
        this.deviceService = deviceService;
    }

    @Override
    public NotificationResult send(final NotificationRequestDto<String> request) throws FirebaseMessagingException {
        final Notification notification = createNotification(request.getTitle(), request.getBody());
        final Message message = Message.builder()
                .setNotification(notification)
                .setToken(request.getTargetToken())
                .build();

        final String messageId = firebaseMessaging.send(message);
        return NotificationResult.success(messageId);
    }

    @Override
    public List<NotificationResult> sendAll(final GeneralNoticeRequestDto request) throws FirebaseMessagingException {
        final List<String> tokens = deviceService.findAllTokens();
        final Notification notification = createNotification(request.title(), request.body());
        final MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(tokens)
                .setNotification(notification)
                .build();

        BatchResponse response = firebaseMessaging.sendEachForMulticast(message);

        if (response.getFailureCount() > 0) {
            throw new MessagePushException(ErrorCode.MESSAGE_NOT_PUSHED);
        }

        return response.getResponses().stream()
                .map(sendResponse -> NotificationResult.success(sendResponse.getMessageId()))
                .toList();
    }

    @Override
    public List<NotificationResult> sendMultiCast(final NotificationRequestDto<List<String>> request) throws FirebaseMessagingException {
        final MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(request.getTargetToken())
                .setNotification(createNotification(request.getTitle(), request.getBody()))
                .build();

        final BatchResponse response = firebaseMessaging.sendMulticast(message);

        if (response.getFailureCount() > 0) {
            throw new MessagePushException(ErrorCode.MESSAGE_NOT_PUSHED);
        }

        return response.getResponses().stream()
                .map(sendResponse -> NotificationResult.success(sendResponse.getMessageId()))
                .toList();
    }

    private Notification createNotification(final String title, final String body) {
        return Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();
    }
}
