package core.pickupbackend.court.mapper;

import core.pickupbackend.court.domain.CourtReview;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CourtReviewRowMapper implements RowMapper<CourtReview> {
    @Override
    public CourtReview mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long courtId = rs.getLong("court_id");
        Long userId = rs.getLong("user_id");
        Integer rating = rs.getInt("rating");
        String content = rs.getString("content");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
        boolean isDeleted = rs.getBoolean("is_deleted");

        return new CourtReview(id, courtId, userId, rating, content, createdAt, updatedAt, isDeleted);
    }
}
