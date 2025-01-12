package core.pickupbackend.device.domain.mapper;

import core.pickupbackend.device.domain.Device;
import core.pickupbackend.device.domain.type.DeviceType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceMapper implements RowMapper<Device> {
    @Override
    public Device mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return new Device(
                rs.getLong("id"),
                rs.getLong("member_id"),
                rs.getString("fcm_token"),
                DeviceType.valueOf(rs.getString("device_type")),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime(),
                rs.getTimestamp("last_login_at") != null
                        ? rs.getTimestamp("last_login_at").toLocalDateTime()
                        : null
        );
    }
}
