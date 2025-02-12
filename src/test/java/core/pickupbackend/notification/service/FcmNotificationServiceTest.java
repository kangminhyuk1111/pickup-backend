package core.pickupbackend.notification.service;

import core.pickupbackend.global.exception.MessagePushException;
import core.pickupbackend.notification.dto.reqeust.NotificationCommand;
import core.pickupbackend.notification.dto.response.NotificationResult;
import core.pickupbackend.notification.fake.FakeNotificationService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FcmNotificationServiceTest {
    private FakeNotificationService notificationService = new FakeNotificationService();

    @Test
    void 단일_알림_전송_성공() {
        // given
        NotificationCommand<String> request = new NotificationCommand<>(
                "test-fcm-token",
                "테스트 제목",
                "테스트 내용"
        );

        // when
        NotificationResult result = notificationService.send(request);

        // then
        List<FakeNotificationService.NotificationRecord> notifications = notificationService.getSentNotifications();
        assertThat(notifications).hasSize(1);
        assertThat(notifications.get(0).title()).isEqualTo("테스트 제목");
        assertThat(notifications.get(0).token()).isEqualTo("test-fcm-token");
    }

    @Test
    void 다건_알림_전송_성공() {
        NotificationCommand<List<String>> request = new NotificationCommand<>(
                List.of("test-fcm-token", "test-fcm-token2"),
                "테스트 제목",
                "테스트 내용"
        );

        final List<NotificationResult> notificationResults = notificationService.sendMulti(request);

        assertThat(notificationResults).hasSize(2);

        for (NotificationResult notificationResult : notificationResults) {
            System.out.println(notificationResult.toString());
        }
    }

    @Test
    void 알림_전송_실패() {
        // given
        notificationService.setShouldFail(true);
        NotificationCommand<String> request = new NotificationCommand<>(
                "token", "제목", "내용"
        );

        // when
        assertThatThrownBy(() -> notificationService.send(request)).isInstanceOf(MessagePushException.class);

        // then
        assertThat(notificationService.getSentNotifications()).isEmpty();
    }
}