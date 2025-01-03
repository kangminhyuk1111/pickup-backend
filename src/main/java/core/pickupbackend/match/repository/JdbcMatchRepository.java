package core.pickupbackend.match.repository;

import core.pickupbackend.global.exception.ApplicationMatchException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.MatchStatus;
import core.pickupbackend.match.mapper.MatchRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMatchRepository implements MatchRepository {

    private final JdbcTemplate jdbcTemplate;
    private final MatchRowMapper rowMapper;

    public JdbcMatchRepository(final JdbcTemplate jdbcTemplate, final MatchRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public Match save(Match match) {
        String sql = "INSERT INTO `match` (title, description, court_name, location, date, time, level, current_players, max_players, cost, rules, host_id, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

        jdbcTemplate.update(sql,
                match.getTitle(),
                match.getDescription(),
                match.getCourtName(),
                match.getLocation(),
                match.getDate(),
                match.getTime(),
                match.getLevel().name(),
                match.getCurrentPlayers(),
                match.getMaxPlayers(),
                match.getCost(),
                match.getRules(),
                match.getHostId(),
                match.getStatus().name());

        return findByHostId(match.getHostId()).orElseThrow(() -> new ApplicationMatchException(ErrorCode.NOT_FOUND_MATCH));
    }

    @Override
    public Optional<Match> findById(Long id) {
        String sql = "SELECT * FROM `match` WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public List<Match> findAll() {
        String sql = "SELECT * FROM `match`";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public Optional<Match> findByHostId(final Long hostId) {
        String sql = "SELECT * FROM `match` WHERE host_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, hostId));
    }

    @Override
    public void update(Match match) {
        String sql = "UPDATE `match` SET title = ?, description = ?, court_name = ?, location = ?, date = ?, time = ?, level = ?, current_players = ?, max_players = ?, cost = ?, rules = ?, status = ?, updated_at = NOW() WHERE id = ?";

        jdbcTemplate.update(sql,
                match.getTitle(),
                match.getDescription(),
                match.getCourtName(),
                match.getLocation(),
                match.getDate(),
                match.getTime(),
                match.getLevel().name(),
                match.getCurrentPlayers(),
                match.getMaxPlayers(),
                match.getCost(),
                match.getRules(),
                match.getStatus().name(),
                match.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM `match` WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
