package core.pickupbackend.device.service;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.dto.FindByTokenRequest;
import core.pickupbackend.device.dto.UpdateDeviceReqeustDto;
import core.pickupbackend.device.dto.CreateDeviceDto;
import core.pickupbackend.device.dto.DeleteDeviceRequestDto;
import core.pickupbackend.device.repository.DeviceRepository;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /* 기기 정보 저장 */
    @Transactional
    public Device save(final CreateDeviceDto createDeviceDto) {
        return deviceRepository.save(createDeviceDto.toEntity());
    }

    /* 모든 기기 정보 조회 */
    public List<Device> findAllDevice() {
        return deviceRepository.findAll();
    }

    /* fcm token 기준으로 기기 정보 조회 */
    public Device findDeviceByFcmToken(final FindByTokenRequest dto) {
        return deviceRepository.findByFcmToken(dto.fcmToken()).orElseThrow(() -> new ApplicationException(ErrorCode.DEVICE_NOT_FOUND));
    }

    /* 기기정보에 유저 id 등록 */
    @Transactional
    public Device updateDeviceByMemberId(final UpdateDeviceReqeustDto dto) {
        final Device device = deviceRepository.findById(dto.deviceId()).orElseThrow(() -> new ApplicationException(ErrorCode.DEVICE_NOT_FOUND));
        final Device updatedDevice = device.updateMemberId(dto.memberId());
        return deviceRepository.updateByMemberId(updatedDevice);
    }

    /* 유저 id로 기기 정보 조회 */
    public List<Device> findDeviceByMemberId(final FindDeviceByMemberIdRequestDto dto) {
        return deviceRepository.findByMemberId(dto.memberId());
    }

    /* 토큰 기준으로 정보 삭제 */
    @Transactional
    public void deleteByToken(final DeleteDeviceRequestDto dto) {
        final String fcmToken = dto.getFcmToken();
        deviceRepository.deleteByFcmToken(fcmToken);
    }
}
