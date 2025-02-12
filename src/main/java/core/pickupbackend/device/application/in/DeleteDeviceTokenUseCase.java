package core.pickupbackend.device.application.in;

import core.pickupbackend.device.dto.request.DeleteDeviceRequestDto;

public interface DeleteDeviceTokenUseCase {
    void deleteToken(DeleteDeviceRequestDto deleteDeviceRequestDto);
}
