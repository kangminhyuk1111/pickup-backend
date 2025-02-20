package core.pickupbackend.device.fake;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.application.out.DeviceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class FakeDeviceRepository implements DeviceRepository {
    private final Map<Long, Device> store = new ConcurrentHashMap<>();
    private Long sequence = 0L;

    @Override
    public Device save(Device device) {
        if (device.getId() == null) {
            Device newDevice = new Device(
                    ++sequence,
                    device.getMemberId(),
                    device.getFcmToken(),
                    device.getDeviceType(),
                    device.getLastLoginAt(),
                    device.getCreatedAt(),
                    device.getUpdatedAt()
            );
            store.put(sequence, newDevice);
            return newDevice;
        }
        store.put(device.getId(), device);
        return device;
    }

    @Override
    public Optional<Device> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Device> findByFcmToken(String fcmToken) {
        return store.values().stream()
                .filter(device -> device.getFcmToken().equals(fcmToken))
                .findFirst();
    }

    @Override
    public List<Device> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public List<Device> findByMemberId(final Long memberId) {
        return store.values().stream().filter(value -> value.getMemberId().equals(memberId)).toList();
    }

    @Override
    public void deleteByFcmToken(final String fcmToken) {
        final Optional<Device> findByFcmToken = findByFcmToken(fcmToken);
        findByFcmToken.ifPresent(device -> store.remove(device.getId()));
    }

    @Override
    public void updateStatus(final Long deviceId, final boolean status) {
        store.computeIfPresent(deviceId, (key, existingDevice) ->
                new Device(
                        existingDevice.getId(),
                        existingDevice.getMemberId(),
                        existingDevice.getFcmToken(),
                        existingDevice.getDeviceType(),
                        status, // active 상태 업데이트
                        existingDevice.getLastLoginAt(),
                        existingDevice.getCreatedAt(),
                        LocalDateTime.now()  // updatedAt 현재 시간으로 업데이트
                )
        );
    }
}
