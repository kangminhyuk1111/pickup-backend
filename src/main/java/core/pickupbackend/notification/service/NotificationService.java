package core.pickupbackend.notification.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import core.pickupbackend.notification.dto.NotificationRequestDto;

import java.util.List;

public interface NotificationService {

    void send(NotificationRequestDto<String> notificationRequestDto) throws FirebaseMessagingException;

    void sendMultiCast(NotificationRequestDto<List<String>> notificationRequestDto) throws FirebaseMessagingException;
}
