package core.pickupbackend.device.fake;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.repository.DeviceRepository;

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
    public Optional<Device> findByToken(String deviceToken) {
        return store.values().stream()
                .filter(device -> device.getFcmToken().equals(deviceToken))
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
    public void updateById(Long id) {
        store.computeIfPresent(id, (key, device) ->
                new Device(
                        device.getId(),
                        device.getMemberId(),
                        device.getFcmToken(),
                        device.getDeviceType(),
                        LocalDateTime.now(),
                        device.getCreatedAt(),
                        LocalDateTime.now()
                )
        );
    }
}
