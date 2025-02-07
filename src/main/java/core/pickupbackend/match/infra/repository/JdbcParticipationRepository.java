package core.pickupbackend.match.infra.repository;

import core.pickupbackend.match.application.out.ParticipationRepository;
import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.domain.mapper.ParticipationRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcParticipationRepository implements ParticipationRepository {

    private final static Logger logger = LoggerFactory.getLogger(JdbcParticipationRepository.class);

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Participation> rowMapper;

    public JdbcParticipationRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        rowMapper = new ParticipationRowMapper();
    }

    @Override
    public Participation createParticipation(final Participation participation) {
        String sql = """
            INSERT INTO match_participation 
            (user_id, matching_id, status, message, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, participation.getUserId());
            ps.setLong(2, participation.getMatchingId());
            ps.setString(3, String.valueOf(participation.getStatus()));
            ps.setString(4, participation.getMessage());
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            logger.info(ps.toString());
            return ps;
        }, keyHolder);

        logger.info("Participation created: {}", participation);

        long generatedId = keyHolder.getKey().longValue();
        return findParticipationById(generatedId);
    }

    @Override
    public List<Participation> findParticipationsByMatchId(final Long matchId) {
        String sql = "SELECT * FROM match_participation WHERE matching_id = ?";
        return jdbcTemplate.query(sql, rowMapper, matchId);
    }

    @Override
    public Participation findParticipationById(final Long id) {
        String sql = "SELECT * FROM match_participation WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Participation updateParticipation(final Participation participation) {
        String sql = "UPDATE match_participation "
                + "SET status = ?, message = ?, updated_at = ? "
                + "WHERE id = ?";

        int updatedRows = jdbcTemplate.update(sql,
                participation.getStatus().name(),
                participation.getMessage(),
                Timestamp.valueOf(LocalDateTime.now()),
                participation.getId()
        );

        if (updatedRows == 0) {
            return null;
        }

        return findParticipationById(participation.getId());
    }

    @Override
    public void deleteParticipation(final long id) {
        String sql = "DELETE FROM match_participation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Participation> findAllParticipation() {
        String sql = "SELECT * FROM match_participation ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Participation> findByMatchId(final long matchId) {
        String sql = "SELECT * FROM match_participation WHERE matching_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, rowMapper, matchId);
    }

    public List<Participation> findByMemberId(final long memberId) {
        String sql = "SELECT * FROM match_participation WHERE user_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, rowMapper, memberId);
    }

    public boolean existsByMatchIdAndMemberId(final long matchId, final long memberId) {
        String sql = "SELECT COUNT(*) FROM match_participation WHERE matching_id = ? AND user_id = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, matchId, memberId);
        return count > 0;
    }
}
