package core.pickupbackend.device.service;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.dto.CreateDeviceDto;
import core.pickupbackend.device.repository.DeviceRepository;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.repository.MemberRepository;
import core.pickupbackend.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device save(final CreateDeviceDto createDeviceDto) {
        return deviceRepository.save(createDeviceDto.toEntity());
    }
}
