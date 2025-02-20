package core.pickupbackend.device.service;

import core.pickupbackend.device.application.service.DefaultDeviceService;
import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.domain.type.DeviceType;
import core.pickupbackend.device.dto.request.*;
import core.pickupbackend.device.fake.FakeDeviceRepository;
import core.pickupbackend.device.application.out.DeviceRepository;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import core.pickupbackend.member.domain.vo.Password;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeviceServiceTest {

    private final DeviceRepository deviceRepository = new FakeDeviceRepository();

    private final DefaultDeviceService deviceService = new DefaultDeviceService(deviceRepository);

    @Test
    void 디바이스_등록_테스트() {
        final Device device = deviceService.save(createTestDeviceDto());

        assertThat(device).isNotNull();
        assertThat(device.getDeviceType()).isEqualTo(DeviceType.ANDROID);
    }

    @Test
    void 디바이스_목록_조회_테스트() {
        final CreateDeviceDto dto = createTestDeviceDto();

        deviceService.save(dto);
        List<Device> devices =  deviceService.findAllDevice();

        assertThat(devices).isNotNull();
        assertThat(devices.size()).isEqualTo(1);
    }

    @Test
    void 디바이스의_멤버ID를_업데이트() {
        final CreateDeviceDto dto = createTestDeviceDto();
        final Member member = createTestMember();
        final Device device = deviceService.save(dto);
        final Device findDevice = deviceService.findDeviceByFcmToken(new FindByTokenRequest(device.getFcmToken()));
        final UpdateDeviceRequestDto updateDeviceRequestDto = new UpdateDeviceRequestDto(findDevice.getId(), false);

        deviceService.updateStatus(updateDeviceRequestDto);

        final Device updatedDevice = deviceRepository.findById(findDevice.getId()).get();
        assertThat(updatedDevice.isStatus()).isFalse();
    }

    @Test
    void 디바이스를_멤버ID_기준으로_조회() {
        final Member member = createTestMember();
        final Device device = deviceService.save(createTestDeviceDto());

        final FindDeviceByMemberIdRequestDto findDeviceByMemberIdRequestDto = new FindDeviceByMemberIdRequestDto(member.getId());

        final List<Device> findDevices = deviceService.findDeviceByMemberId(findDeviceByMemberIdRequestDto);

        assertThat(findDevices.size()).isEqualTo(1);
        assertThat(findDevices.stream().map(findDevice -> findDevice.getFcmToken().equals(device.getFcmToken())).findFirst()).isNotNull();
    }

    @Test
    void 토큰을_기준으로_디바이스_삭제() {
        final CreateDeviceDto createDeviceDto = createTestDeviceDto();
        final Device device = deviceService.save(createDeviceDto);
        final String fcmToken = device.getFcmToken();
        final DeleteDeviceRequestDto deleteDeviceDto = new DeleteDeviceRequestDto(fcmToken);

        deviceService.deleteToken(deleteDeviceDto);

        assertThatThrownBy(() -> deviceService.findDeviceByFcmToken(new FindByTokenRequest(fcmToken))).isInstanceOf(ApplicationException.class);
    }

    private CreateDeviceDto createTestDeviceDto() {
        final String fcmToken = "eP2aFd0RToWVLlJwDWNHvG:APA91bFSrT3OQFYe1gKbFVDtHFaZmPxn2hHGdT7P_QFhRTcXL4HBfDzW6DxTZqPQfGQ7vKY8jVqC4vzW34XLwLvQbXjP7vH3h9GLzLmVKUkE2TzfGq0ZQGDl7KxOv3vYnO4Vkjl1s8Fc";
        final DeviceType deviceType = DeviceType.ANDROID;
        return new CreateDeviceDto(fcmToken, 1L, deviceType, true);
    }
    private Member createTestMember() {
        return new Member(1L, "test@gmail.com", new Password(new BCryptPasswordEncoder(), "testpassword"), "test", 200, 100, Position.SF, Level.BEGINNER);
    }
}
