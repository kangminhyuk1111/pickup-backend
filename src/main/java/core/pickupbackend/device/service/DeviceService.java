package core.pickupbackend.device.service;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.repository.DeviceRepository;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device save(final Device device) {
        return deviceRepository.save(device);
    }
}
