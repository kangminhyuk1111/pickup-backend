package core.pickupbackend.device.domain;

import core.pickupbackend.device.domain.type.DeviceType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DeviceTest {

    @Test
    void 기본_생성자로_디바이스_객체를_생성한다() {
        // when
        Device device = new Device();

        // then
        assertThat(device).isNotNull();
    }

    @Test
    void 모든_필드를_가진_디바이스_객체를_생성한다() {
        // given
        Long id = 1L;
        Long memberId = 100L;
        String fcmToken = "test-fcm-token";
        DeviceType deviceType = DeviceType.ANDROID;
        LocalDateTime now = LocalDateTime.now();

        // when
        Device device = new Device(id, memberId, fcmToken, deviceType, now, now, now);

        // then
        assertThat(device.getId()).isEqualTo(id);
        assertThat(device.getMemberId()).isEqualTo(memberId);
        assertThat(device.getFcmToken()).isEqualTo(fcmToken);
        assertThat(device.getDeviceType()).isEqualTo(deviceType);
        assertThat(device.getCreatedAt()).isEqualTo(now);
        assertThat(device.getUpdatedAt()).isEqualTo(now);
        assertThat(device.getLastLoginAt()).isEqualTo(now);
    }

    @Test
    void 디바이스_아이디를_조회한다() {
        // given
        Long id = 1L;
        Device device = createDevice(id);

        // when
        Long deviceId = device.getId();

        // then
        assertThat(deviceId).isEqualTo(id);
    }

    @Test
    void 회원_아이디를_조회한다() {
        // given
        Long memberId = 100L;
        Device device = createDeviceWithMemberId(memberId);

        // when
        Long deviceMemberId = device.getMemberId();

        // then
        assertThat(deviceMemberId).isEqualTo(memberId);
    }

    @Test
    void FCM_토큰을_조회한다() {
        // given
        String fcmToken = "test-fcm-token";
        Device device = createDeviceWithToken(fcmToken);

        // when
        String deviceFcmToken = device.getFcmToken();

        // then
        assertThat(deviceFcmToken).isEqualTo(fcmToken);
    }

    @Test
    void 디바이스_타입을_조회한다() {
        // given
        DeviceType deviceType = DeviceType.IOS;
        Device device = createDeviceWithType(deviceType);

        // when
        DeviceType type = device.getDeviceType();

        // then
        assertThat(type).isEqualTo(deviceType);
    }

    @Test
    void 생성_시간을_조회한다() {
        // given
        LocalDateTime createdAt = LocalDateTime.now();
        Device device = createDeviceWithTimes(createdAt, null, null);

        // when
        LocalDateTime deviceCreatedAt = device.getCreatedAt();

        // then
        assertThat(deviceCreatedAt).isEqualTo(createdAt);
    }

    @Test
    void 수정_시간을_조회한다() {
        // given
        LocalDateTime updatedAt = LocalDateTime.now();
        Device device = createDeviceWithTimes(null, updatedAt, null);

        // when
        LocalDateTime deviceUpdatedAt = device.getUpdatedAt();

        // then
        assertThat(deviceUpdatedAt).isEqualTo(updatedAt);
    }

    @Test
    void 마지막_로그인_시간을_조회한다() {
        // given
        LocalDateTime lastLoginAt = LocalDateTime.now();
        Device device = createDeviceWithTimes(null, null, lastLoginAt);

        // when
        LocalDateTime deviceLastLoginAt = device.getLastLoginAt();

        // then
        assertThat(deviceLastLoginAt).isEqualTo(lastLoginAt);
    }

    private Device createDevice(Long id) {
        return new Device(
                id,
                1L,
                "fcm-token",
                DeviceType.ANDROID,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private Device createDeviceWithMemberId(Long memberId) {
        return new Device(
                1L,
                memberId,
                "fcm-token",
                DeviceType.ANDROID,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private Device createDeviceWithToken(String fcmToken) {
        return new Device(
                1L,
                1L,
                fcmToken,
                DeviceType.ANDROID,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private Device createDeviceWithType(DeviceType deviceType) {
        return new Device(
                1L,
                1L,
                "fcm-token",
                deviceType,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private Device createDeviceWithTimes(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLoginAt) {
        return new Device(
                1L,
                1L,
                "fcm-token",
                DeviceType.ANDROID,
                createdAt != null ? createdAt : LocalDateTime.now(),
                updatedAt != null ? updatedAt : LocalDateTime.now(),
                lastLoginAt != null ? lastLoginAt : LocalDateTime.now()
        );
    }
}