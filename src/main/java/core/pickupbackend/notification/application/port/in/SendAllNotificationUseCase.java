package core.pickupbackend.notification.application.port.in;

import com.google.firebase.messaging.FirebaseMessagingException;
import core.pickupbackend.notification.dto.reqeust.GeneralNoticeCommand;
import core.pickupbackend.notification.dto.response.NotificationResult;

import java.util.List;

public interface SendAllNotificationUseCase {
    List<NotificationResult> sendAll(GeneralNoticeCommand generalNoticeRequestDto) throws FirebaseMessagingException;
}
