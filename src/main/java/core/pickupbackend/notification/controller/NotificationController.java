package core.pickupbackend.notification.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import core.pickupbackend.notification.dto.reqeust.NotificationRequestDto;
import core.pickupbackend.notification.service.FcmNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final static Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final FcmNotificationService pushMessageService;

    public NotificationController(final FcmNotificationService pushMessageService) {
        this.pushMessageService = pushMessageService;
    }

    @PostMapping("/multi")
    public void sendAll(@RequestBody final NotificationRequestDto<List<String>> pushRequestDto) throws FirebaseMessagingException {
        pushMessageService.sendMultiCast(pushRequestDto);
    }

    @PostMapping("/single")
    public void sendSingle(@RequestBody final NotificationRequestDto<String> pushRequestDto) throws FirebaseMessagingException {
        pushMessageService.send(pushRequestDto);
    }
}
