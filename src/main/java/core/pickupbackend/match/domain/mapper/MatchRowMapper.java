package core.pickupbackend.match.domain.mapper;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.MatchStatus;
import core.pickupbackend.member.domain.type.Level;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MatchRowMapper implements RowMapper<Match> {

    @Override
    public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
        Match match = new Match();
        match.setId(rs.getLong("id"));
        match.setTitle(rs.getString("title"));
        match.setDescription(rs.getString("description"));
        match.setCourtName(rs.getString("court_name"));
        match.setLocation(rs.getString("location"));
        match.setDate(rs.getDate("date").toLocalDate());
        match.setTime(rs.getTime("time").toLocalTime());
        match.setLevel(Level.valueOf(rs.getString("level")));
        match.setCurrentPlayers(rs.getInt("current_players"));
        match.setMaxPlayers(rs.getInt("max_players"));
        match.setCost(rs.getLong("cost"));
        match.setRules(rs.getString("rules"));
        match.setHostId(rs.getLong("host_id"));
        match.setStatus(MatchStatus.valueOf(rs.getString("status")));
        match.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        match.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return match;
    }
}
