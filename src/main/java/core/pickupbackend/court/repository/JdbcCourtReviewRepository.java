package core.pickupbackend.court.repository;

import core.pickupbackend.court.domain.CourtReview;
import core.pickupbackend.court.mapper.CourtReviewRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCourtReviewRepository implements CourtReviewRepository{

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<CourtReview> rowMapper = new CourtReviewRowMapper();

    public JdbcCourtReviewRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CourtReview> getReviewById(final Long courtId) {
        final String sql = """
            SELECT * FROM court_reviews WHERE court_id = ?
            """;

        return jdbcTemplate.query(sql, rowMapper, courtId);
    }
}
