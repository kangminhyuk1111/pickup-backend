package core.pickupbackend.device.controller;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.dto.CreateDeviceDto;
import core.pickupbackend.device.dto.DeleteDeviceDto;
import core.pickupbackend.device.service.DeviceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(final DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public Device createDevice(@RequestBody CreateDeviceDto createDeviceDto) {
        return this.deviceService.save(createDeviceDto);
    }

    @DeleteMapping
    public void deleteDevice(@RequestBody DeleteDeviceDto deleteDeviceDto) {
        this.deviceService.deleteByToken(deleteDeviceDto);
    }
}
