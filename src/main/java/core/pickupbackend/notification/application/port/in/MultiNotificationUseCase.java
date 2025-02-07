package core.pickupbackend.notification.application.port.in;

import com.google.firebase.messaging.FirebaseMessagingException;
import core.pickupbackend.notification.dto.reqeust.NotificationCommand;
import core.pickupbackend.notification.dto.response.NotificationResult;

import java.util.List;

public interface MultiNotificationUseCase {
    List<NotificationResult> sendMultiCast(NotificationCommand<List<String>> notificationRequestDto) throws FirebaseMessagingException;
}
