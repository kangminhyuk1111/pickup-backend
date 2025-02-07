package core.pickupbackend.device.infra.web.controller;

import core.pickupbackend.device.application.in.DeviceService;
import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.dto.CreateDeviceDto;
import core.pickupbackend.device.dto.DeleteDeviceRequestDto;
import core.pickupbackend.device.dto.DeviceUnregisterRequest;
import core.pickupbackend.device.application.service.DefaultDeviceService;
import core.pickupbackend.global.common.code.StatusCode;
import core.pickupbackend.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public BaseResponse<Device> registerDevice(@RequestBody CreateDeviceDto dto) {
        Device device = deviceService.save(dto);
        return new BaseResponse<>(StatusCode.SUCCESS, device);
    }

    /* 3. 알림 비활성화 또는 앱 삭제 시 디바이스 토큰 삭제 */
    @Operation(summary = "디바이스 토큰 삭제")
    @DeleteMapping
    public BaseResponse<Void> unregisterDevice(@RequestBody DeviceUnregisterRequest request) {
        deviceService.deleteToken(new DeleteDeviceRequestDto(request.fcmToken()));
        return new BaseResponse<>(StatusCode.SUCCESS, null);
    }
}
