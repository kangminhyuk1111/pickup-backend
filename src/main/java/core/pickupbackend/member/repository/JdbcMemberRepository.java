package core.pickupbackend.member.repository;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.mapper.MemberRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository {

    private final JdbcTemplate template;

    private final MemberRowMapper rowMapper = new MemberRowMapper();

    public JdbcMemberRepository(final JdbcTemplate template) {
        this.template = template;
    }

    public Member save(Member member) {
        String sql = """
            INSERT INTO users (email, password, nickname, profile_image, 
                             height, weight, position, level, manner_score, 
                             created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getEmail());
            ps.setString(2, member.getPassword());
            ps.setString(3, member.getNickname());
            ps.setString(4, member.getProfileImage());
            ps.setInt(5, member.getHeight());
            ps.setInt(6, member.getWeight());
            ps.setString(7, member.getPosition().name());
            ps.setString(8, member.getLevel().name());
            ps.setDouble(9, member.getMannerScore());
            ps.setTimestamp(10, Timestamp.valueOf(member.getCreatedAt()));
            ps.setTimestamp(11, Timestamp.valueOf(member.getUpdatedAt()));
            return ps;
        }, keyHolder);

        return findById(keyHolder.getKey().longValue())
                .orElseThrow(() -> new RuntimeException("Failed to save member"));
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM users";
        return template.query(sql, rowMapper);
    }

    public Optional<Member> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        List<Member> results = template.query(sql, rowMapper, id);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public Optional<Member> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<Member> results = template.query(sql, rowMapper, email);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int affectedRows = template.update(sql, id);
        return affectedRows == 1;
    }
}
