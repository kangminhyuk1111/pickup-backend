package core.pickupbackend.notification.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import core.pickupbackend.notification.dto.reqeust.NotificationRequestDto;
import core.pickupbackend.notification.dto.response.NotificationResult;

import java.util.List;

public interface NotificationService {

    NotificationResult send(NotificationRequestDto<String> notificationRequestDto) throws FirebaseMessagingException;

    List<NotificationResult> sendMultiCast(NotificationRequestDto<List<String>> notificationRequestDto) throws FirebaseMessagingException;
}
