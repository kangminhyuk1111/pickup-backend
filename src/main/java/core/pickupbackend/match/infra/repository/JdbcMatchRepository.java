package core.pickupbackend.match.infra.repository;L

import core.pickupbackend.global.exception.ApplicationMatchException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.match.application.out.MatchRepository;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.mapper.MatchRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMatchRepository implements MatchRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcMatchRepository.class);

    private final JdbcTemplate jdbcTemplate;
    private final MatchRowMapper rowMapper;

    public JdbcMatchRepository(final JdbcTemplate jdbcTemplate, final MatchRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public Match save(Match match) {
        String sql = "INSERT INTO `match` (title, description, court_name, district, location_detail, date, time, level, current_players, max_players, cost, rules, host_id, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, match.getTitle());
            ps.setString(2, match.getDescription());
            ps.setString(3, match.getCourtName());
            ps.setString(4, match.getDistrict());
            ps.setString(5, match.getLocationDetail());
            ps.setDate(6, Date.valueOf(match.getDate()));
            ps.setTime(7, Time.valueOf(match.getTime()));
            ps.setString(8, match.getLevel().name());
            ps.setInt(9, match.getCurrentPlayers());
            ps.setInt(10, match.getMaxPlayers());
            ps.setLong(11, match.getCost());
            ps.setString(12, match.getRules());
            ps.setLong(13, match.getHostId());
            return ps;
        }, keyHolder);

        Long generatedId = keyHolder.getKey().longValue();

        return findById(generatedId).orElseThrow(() -> new ApplicationMatchException(ErrorCode.NOT_FOUND_MATCH));
    }

    @Override
    public Optional<Match> findById(Long id) {
        String sql = "SELECT * FROM `match` WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public List<Match> findAll(Integer page, Integer size) {
        int offset = page * size;
        String sql = "SELECT * FROM `match` ORDER BY `date` DESC, `time` DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, rowMapper, size, offset);
    }

    public Optional<Match> findByHostId(final Long hostId) {
        String sql = "SELECT * FROM `match` WHERE host_id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, hostId));
    }

    @Override
    public Match update(Long id, Match match) {
        String sql = "UPDATE `match` SET title = ?, description = ?, court_name = ?, district = ? , locationDetail = ?, date = ?, time = ?, level = ?, current_players = ?, max_players = ?, cost = ?, rules = ?, status = ?, updated_at = NOW() WHERE id = ?";

        jdbcTemplate.update(sql,
                match.getTitle(),
                match.getDescription(),
                match.getCourtName(),
                match.getDistrict(),
                match.getLocationDetail(),
                match.getDate(),
                match.getTime(),
                match.getLevel().name(),
                match.getCurrentPlayers(),
                match.getMaxPlayers(),
                match.getCost(),
                match.getRules(),
                match.getStatus().name(),
                id);

        return findById(id).orElseThrow(() -> new ApplicationMatchException(ErrorCode.NOT_FOUND_MATCH));
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM `match` WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Match> findByMemberId(final Long memberId) {
        String sql = "SELECT * FROM `match` WHERE host_id = ?";
        return jdbcTemplate.query(sql, rowMapper, memberId);
    }

    @Override
    public Integer countAll() {
        String sql = "SELECT COUNT(*) FROM `match`";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<String> findAllDistricts() {
        String sql = "SELECT name FROM `districts`";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
