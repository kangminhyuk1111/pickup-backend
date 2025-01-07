package core.pickupbackend.match.domain.mapper;

import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.domain.ParticipationStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipationRowMapper implements RowMapper<Participation> {

    @Override
    public Participation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Participation(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("matching_id"),
                ParticipationStatus.valueOf(rs.getString("status")),
                rs.getString("message"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime()
        );
    }
}
