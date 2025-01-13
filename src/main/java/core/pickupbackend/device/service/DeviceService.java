package core.pickupbackend.device.service;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.dto.CreateDeviceDto;
import core.pickupbackend.device.dto.DeleteDeviceDto;
import core.pickupbackend.device.repository.DeviceRepository;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
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

    public List<Device> findAllDevice() {
        return deviceRepository.findAll();
    }

    public Device findDeviceByFcmToken(final String fcmToken) {
        return deviceRepository.findByFcmToken(fcmToken).orElseThrow(() -> new ApplicationException(ErrorCode.DEVICE_NOT_FOUND));
    }

    public Device updateMemberId(final Long memberId, final Device device) {
        final Device updateDevice = device.updateMemberId(memberId);
        return deviceRepository.updateByMemberId(updateDevice);
    }

    public List<Device> findDeviceByMemberId(final Long memberId) {
        return deviceRepository.findByMemberId(memberId);
    }

    public void deleteByToken(final DeleteDeviceDto deleteDeviceDto) {
        final String fcmToken = deleteDeviceDto.getFcmToken();
        deviceRepository.deleteByFcmToken(fcmToken);
    }
}

//엔티티를 넘기는건 아니고 만약에 id만 필요한 경우에 매개변수에 id를 바로 넣는것보다 dto형식으로 안에 값을 넣어서 하는게 추후에 서비스 로직에 다른 매개변수 필요할 때, dto 만 수정하면 되기때문에 더 좋을 것 같다로 이해하면 될까요?
