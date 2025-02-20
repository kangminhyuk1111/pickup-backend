package core.pickupbackend.device.application.in;

import core.pickupbackend.device.dto.request.UpdateDeviceRequestDto;

public interface UpdateDeviceTokenUseCase {
    void updateStatus(UpdateDeviceRequestDto dto);
}
