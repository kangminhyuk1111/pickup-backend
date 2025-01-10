package core.pickupbackend.push.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import core.pickupbackend.member.service.MemberService;
import core.pickupbackend.push.dto.PushRequestDto;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final FirebaseMessaging firebaseMessaging;
    private final MemberService memberService;

    public MessageService(final FirebaseMessaging firebaseMessaging, final MemberService memberService) {
        this.firebaseMessaging = firebaseMessaging;
        this.memberService = memberService;
    }

//    public void sendPush(final PushRequestDto pushRequestDto) throws FirebaseMessagingException {
//        firebaseMessaging.send();
//    }
//
//    public void sendPushMessage(final PushRequestDto pushRequestDto) throws FirebaseMessagingException {
//        FirebaseMessaging.getInstance().sendEachForMulticast();
//    }

    private Message makeMessage(final String targetToken, final  String title, final String body) {
        Notification notification = Notification
                .builder()
                .setTitle(title)
                .setBody(body)
                .build();
        return Message
                .builder()
                .setNotification(notification)
                .setToken(targetToken)
                .build();
    }
}
