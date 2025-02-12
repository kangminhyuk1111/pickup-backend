package core.pickupbackend.device.application.in;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.dto.request.CreateDeviceDto;

public interface SaveDeviceTokenUseCase {
    Device save(CreateDeviceDto createDeviceDto);
}
