package core.pickupbackend.member.domain.mapper;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {
    @Override
    public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Member(
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("nickname"),
                rs.getInt("height"),
                rs.getInt("weight"),
                Position.valueOf(rs.getString("position")),
                Level.valueOf(rs.getString("level"))
        );
    }
}