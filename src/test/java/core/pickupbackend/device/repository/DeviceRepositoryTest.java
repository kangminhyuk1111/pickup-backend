package core.pickupbackend.device.repository;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.domain.type.DeviceType;
import core.pickupbackend.device.fake.FakeDeviceRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class DeviceRepositoryTest {

    private final DeviceRepository repository = new FakeDeviceRepository();

    private final LocalDateTime now = LocalDateTime.now();

    @Test
    void 아이디가_없는_경우_새로운_디바이스를_생성한다() {
        // given
        Device device = createDevice(null, 1L, "token1", DeviceType.ANDROID);

        // when
        Device savedDevice = repository.save(device);

        // then
        assertThat(savedDevice.getId()).isNotNull();
        assertThat(savedDevice.getMemberId()).isEqualTo(device.getMemberId());
        assertThat(savedDevice.getFcmToken()).isEqualTo(device.getFcmToken());
        assertThat(savedDevice.getDeviceType()).isEqualTo(device.getDeviceType());
    }

    @Test
    void 아이디가_있는_경우_기존_디바이스를_업데이트한다() {
        // given
        Device device = createDevice(1L, 1L, "token1", DeviceType.ANDROID);

        // when
        Device savedDevice = repository.save(device);

        // then
        assertThat(savedDevice.getId()).isEqualTo(device.getId());
    }

    @Test
    void 존재하는_아이디로_조회하면_디바이스를_반환한다() {
        // given
        Device device = repository.save(createDevice(null, 1L, "token1", DeviceType.ANDROID));

        // when
        Optional<Device> foundDevice = repository.findById(device.getId());

        // then
        assertThat(foundDevice).isPresent();
        assertThat(foundDevice.get().getId()).isEqualTo(device.getId());
    }

    @Test
    void 존재하지_않는_아이디로_조회하면_빈_옵셔널을_반환한다() {
        // when
        Optional<Device> foundDevice = repository.findById(999L);

        // then
        assertThat(foundDevice).isEmpty();
    }

    @Test
    void 존재하는_토큰으로_조회하면_디바이스를_반환한다() {
        // given
        Device device = repository.save(createDevice(null, 1L, "token1", DeviceType.ANDROID));

        // when
        Optional<Device> foundDevice = repository.findByToken("token1");

        // then
        assertThat(foundDevice).isPresent();
        assertThat(foundDevice.get().getFcmToken()).isEqualTo(device.getFcmToken());
    }

    @Test
    void 존재하지_않는_토큰으로_조회하면_빈_옵셔널을_반환한다() {
        // when
        Optional<Device> foundDevice = repository.findByToken("nonexistent-token");

        // then
        assertThat(foundDevice).isEmpty();
    }

    @Test
    void 디바이스가_없는_경우_빈_리스트를_반환한다() {
        // when
        List<Device> devices = repository.findAll();

        // then
        assertThat(devices).isEmpty();
    }

    @Test
    void 디바이스가_있는_경우_모든_디바이스를_반환한다() {
        // given
        repository.save(createDevice(null, 1L, "token1", DeviceType.ANDROID));
        repository.save(createDevice(null, 2L, "token2", DeviceType.IOS));

        // when
        List<Device> devices = repository.findAll();

        // then
        assertThat(devices).hasSize(2);
    }

    @Test
    void 존재하는_아이디로_삭제하면_디바이스가_제거된다() {
        // given
        Device device = repository.save(createDevice(null, 1L, "token1", DeviceType.ANDROID));

        // when
        repository.deleteById(device.getId());

        // then
        assertThat(repository.findById(device.getId())).isEmpty();
    }

    @Test
    void 디바이스_삭제시_다른_디바이스는_영향받지_않는다() {
        // given
        Device device1 = repository.save(createDevice(null, 1L, "token1", DeviceType.ANDROID));
        Device device2 = repository.save(createDevice(null, 2L, "token2", DeviceType.IOS));

        // when
        repository.deleteById(device1.getId());

        // then
        assertThat(repository.findById(device2.getId())).isPresent();
    }

    private Device createDevice(Long id, Long memberId, String fcmToken, DeviceType deviceType) {
        return new Device(
                id,
                memberId,
                fcmToken,
                deviceType,
                now,
                now,
                now
        );
    }
}
