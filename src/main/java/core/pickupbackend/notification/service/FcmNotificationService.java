package core.pickupbackend.notification.service;

import com.google.firebase.messaging.*;
import core.pickupbackend.notification.dto.NotificationRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FcmNotificationService implements NotificationService {

    private final FirebaseMessaging firebaseMessaging;

    public FcmNotificationService(final FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public void send(final NotificationRequestDto<String> pushRequestDto) throws FirebaseMessagingException {
        final Notification notification = Notification
                .builder()
                .setTitle(pushRequestDto.getTitle())
                .setBody(pushRequestDto.getBody())
                .build();

        final Message message = Message
                .builder()
                .setNotification(notification)
                .setToken(pushRequestDto.getTargetToken())
                .build();

        firebaseMessaging.send(message);
    }

    public void sendMultiCast(final NotificationRequestDto<List<String>> pushRequestDto) throws FirebaseMessagingException {
        final MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(pushRequestDto.getTargetToken())
                .setNotification(Notification.builder()
                        .setTitle(pushRequestDto.getTitle())
                        .setBody(pushRequestDto.getBody())
                        .build())
                .build();

        firebaseMessaging.sendMulticast(message);
    }
}
