package core.pickupbackend.device.application.service;

import core.pickupbackend.device.application.in.DeviceService;
import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.application.out.DeviceRepository;
import core.pickupbackend.device.dto.request.*;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultDeviceService implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DefaultDeviceService(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    /* 기기 정보 저장 */
    @Transactional
    @Override
    public Device save(final CreateDeviceDto createDeviceDto) {
        return deviceRepository.save(createDeviceDto.toEntity());
    }

    /* 모든 기기 정보 조회 */
    public List<Device> findAllDevice() {
        return deviceRepository.findAll();
    }

    public List<String> findAllTokens() {
        return deviceRepository.findAll().stream().map(Device::getFcmToken).toList();
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
    @Override
    public void deleteToken(final DeleteDeviceRequestDto dto) {
        final String fcmToken = dto.getFcmToken();
        deviceRepository.deleteByFcmToken(fcmToken);
    }
}
