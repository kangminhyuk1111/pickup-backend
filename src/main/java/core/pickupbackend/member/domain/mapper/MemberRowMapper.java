package core.pickupbackend.member.domain.mapper;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import core.pickupbackend.member.domain.vo.Password;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        // 필수 필드로 Member 객체 생성
        Member member = new Member(
                rs.getLong("id"),
                rs.getString("email"),
                new Password(rs.getString("password")),
                rs.getString("nickname"),
                rs.getObject("height", Integer.class),
                rs.getObject("weight", Integer.class),
                Position.valueOf(rs.getString("position")),
                Level.valueOf(rs.getString("level"))
        );

        // nullable 필드 매핑
        String profileImage = rs.getString("profile_image");
        if (!rs.wasNull()) {
            member.setProfileImage(profileImage);
        }

        // 매너 스코어 매핑
        Double mannerScore = rs.getDouble("manner_score");
        if (!rs.wasNull()) {
            member.setMannerScore(mannerScore);
        }

        // 날짜 필드 매핑
        Timestamp createdAt = rs.getTimestamp("created_at");
        if (!rs.wasNull()) {
            member.setCreatedAt(createdAt.toLocalDateTime());
        }

        Timestamp updatedAt = rs.getTimestamp("updated_at");
        if (!rs.wasNull()) {
            member.setUpdatedAt(updatedAt.toLocalDateTime());
        }

        Timestamp lastLoginAt = rs.getTimestamp("last_login_at");
        if (!rs.wasNull()) {
            member.setLastLoginAt(lastLoginAt.toLocalDateTime());
        }

        return member;
    }
}