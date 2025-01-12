package core.pickupbackend.court.repository;

import core.pickupbackend.court.domain.Court;
import core.pickupbackend.court.mapper.CourtRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCourtRepository implements CourtRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Court> courtRowMapper = new CourtRowMapper();

    public JdbcCourtRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Court> getAllCourts() {
        String sql = """
                    SELECT c.id, c.name, c.location, c.address, c.latitude, c.longitude, c.hoops, c.surface, c.lighting, c.parking, c.rating, c.best_time,
                           GROUP_CONCAT(DISTINCT ci.image_url ORDER BY ci.image_url SEPARATOR ',') AS images,
                           GROUP_CONCAT(DISTINCT cf.facility ORDER BY cf.facility SEPARATOR ',') AS facilities
                    FROM court c
                             LEFT JOIN court_images ci ON c.id = ci.court_id
                             LEFT JOIN court_facilities cf ON c.id = cf.court_id
                    GROUP BY c.id;
                """;
        return jdbcTemplate.query(sql, courtRowMapper);
    }
}
