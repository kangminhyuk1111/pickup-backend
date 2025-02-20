package core.pickupbackend.device.application.out;

import core.pickupbackend.device.domain.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository {

    Device save(Device Device);

    Optional<Device> findById(Long id);

    Optional<Device> findByFcmToken(String deviceToken);

    List<Device> findAll();

    void deleteById(Long id);

    List<Device> findByMemberId(Long memberId);

    void deleteByFcmToken(String fcmToken);

    void updateStatus(Long deviceId, boolean status);
}
