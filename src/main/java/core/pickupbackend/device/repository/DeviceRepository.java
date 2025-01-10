package core.pickupbackend.device.repository;

import core.pickupbackend.device.domain.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository {

    Device save(Device Device);

    Optional<Device> findById(Long id);

    Optional<Device> findByToken(String deviceToken);

    List<Device> findAll();

    void deleteById(Long id);

    void updateById(Long id);
}
