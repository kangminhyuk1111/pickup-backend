package core.pickupbackend.notification.application.port.in;

import com.google.firebase.messaging.FirebaseMessagingException;
import core.pickupbackend.notification.dto.reqeust.NotificationCommand;
import core.pickupbackend.notification.dto.response.NotificationResult;

public interface SingleNotificationUseCase {
    NotificationResult send(NotificationCommand<String> notificationRequestDto) throws FirebaseMessagingException;
}
