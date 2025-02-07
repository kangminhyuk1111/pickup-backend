package core.pickupbackend.device.application.in;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.dto.CreateDeviceDto;

public interface SaveDeviceTokenUseCase {
    Device save(CreateDeviceDto createDeviceDto);
}
