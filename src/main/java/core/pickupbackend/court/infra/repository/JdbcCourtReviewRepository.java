package core.pickupbackend.court.infra.repository;

import core.pickupbackend.court.application.out.LoadCourtReviewPort;
import core.pickupbackend.court.domain.CourtReview;
import core.pickupbackend.court.domain.mapper.CourtReviewRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCourtReviewRepository implements LoadCourtReviewPort {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CourtReview> rowMapper = new CourtReviewRowMapper();

    public JdbcCourtReviewRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CourtReview> loadCourtReviewById(final Long courtId) {
        final String sql = """
            SELECT * FROM court_reviews WHERE court_id = ?
            """;

        return jdbcTemplate.query(sql, rowMapper, courtId);
    }
}
