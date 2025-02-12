package core.pickupbackend.device.infra.web.controller;

import core.pickupbackend.device.application.in.DeviceService;
import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.dto.request.CreateDeviceDto;
import core.pickupbackend.device.dto.request.DeleteDeviceRequestDto;
import core.pickupbackend.device.dto.request.DeviceUnregisterRequest;
import core.pickupbackend.device.application.service.DefaultDeviceService;
import core.pickupbackend.device.dto.response.DeviceResponse;
import core.pickupbackend.global.common.code.StatusCode;
import core.pickupbackend.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "디바이스 API")
@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(final DefaultDeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /* 1. 알림 허용 시 디바이스 토큰 저장 */
    @Operation(summary = "디바이스 토큰 저장")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceResponse registerDevice(@RequestBody CreateDeviceDto dto) {
        final Device device = deviceService.save(dto);
        return DeviceResponse.from(device);
    }

    /* 2. 알림 비활성화 또는 앱 삭제 시 디바이스 토큰 삭제 */
    @Operation(summary = "디바이스 토큰 삭제")
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unregisterDevice(@RequestBody DeviceUnregisterRequest request) {
        deviceService.deleteToken(new DeleteDeviceRequestDto(request.fcmToken()));
    }
}
