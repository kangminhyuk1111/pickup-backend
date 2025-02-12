package core.pickupbackend.notification.infra.web.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import core.pickupbackend.notification.application.port.in.NotificationPort;
import core.pickupbackend.notification.application.service.FcmNotificationService;
import core.pickupbackend.notification.dto.reqeust.GeneralNoticeCommand;
import core.pickupbackend.notification.dto.reqeust.NotificationCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "푸시 알림 API")
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final static Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationPort pushMessageService;

    public NotificationController(final FcmNotificationService pushMessageService) {
        this.pushMessageService = pushMessageService;
    }

    @Operation(summary = "전체 푸시알림 요청", security = { @SecurityRequirement(name = "bearerAuth") })
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendAll(@RequestBody final GeneralNoticeCommand pushRequestDto) throws FirebaseMessagingException {
        logger.debug("send all request: {}", pushRequestDto);
        pushMessageService.sendAll(pushRequestDto);
    }

    @Operation(summary = "푸시알림 다건 요청", security = { @SecurityRequirement(name = "bearerAuth") })
    @PostMapping("/multi")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendMultiple(@RequestBody final NotificationCommand<List<String>> pushRequestDto) throws FirebaseMessagingException {
        logger.debug("send multi request: {}", pushRequestDto);
        pushMessageService.sendMultiCast(pushRequestDto);
    }

    @Operation(summary = "푸시알림 단건 요청", security = { @SecurityRequirement(name = "bearerAuth") })
    @PostMapping("/single")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void sendSingle(@RequestBody final NotificationCommand<String> pushRequestDto) throws FirebaseMessagingException {
        logger.debug("send single request: {}", pushRequestDto);
        pushMessageService.send(pushRequestDto);
    }
}
