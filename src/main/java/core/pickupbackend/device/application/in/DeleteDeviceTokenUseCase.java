package core.pickupbackend.device.application.in;

import core.pickupbackend.device.dto.DeleteDeviceRequestDto;

public interface DeleteDeviceTokenUseCase {
    void deleteToken(DeleteDeviceRequestDto deleteDeviceRequestDto);
}
