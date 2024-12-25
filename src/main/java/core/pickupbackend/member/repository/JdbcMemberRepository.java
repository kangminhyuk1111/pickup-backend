package core.pickupbackend.member.repository;

import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.mapper.MemberRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
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

        // 생성된 키 값을 확인
        Number key = keyHolder.getKey();

        // member 객체에 직접 ID 설정
        Long generatedId = key.longValue();
        member.setId(generatedId);

        // 설정된 ID 확인
        System.out.println("Member ID after setting: " + member.getId());

        // 데이터베이스에서 조회
        Member foundMember = findById(generatedId)
                .orElseThrow(() -> new RuntimeException("Failed to save member"));

        // 조회된 member의 ID 확인
        System.out.println("Found Member ID: " + foundMember.getId());

        return foundMember;
    }

    @Override
    public Optional<Member> findById(Long id) {
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            return Optional.ofNullable(
                    template.queryForObject(sql, rowMapper, id)  // id 파라미터 전달
            );
        } catch (ApplicationException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<Member> results = template.query(sql, rowMapper, email);
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    @Override
    public Optional<Member> findByNickname(final String nickname) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM users";
        return template.query(sql, rowMapper);
    }

    @Override
    public void update(final Member member) {
        final Member findById = findById(member.getId()).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));

        String sql = "UPDATE users SET email = ?, password = ?, nickname = ?, profile_image = ?, " +
                "height = ?, weight = ?, position = ?, level = ?, manner_score = ?, " +
                "updated_at = ?, last_login_at = ? WHERE id = ?";

        int updatedRows = template.update(sql,
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getProfileImage(),
                member.getHeight(),
                member.getWeight(),
                member.getPosition().name(),
                member.getLevel().name(),
                member.getMannerScore(),
                Timestamp.valueOf(member.getUpdatedAt()),
                Optional.ofNullable(member.getLastLoginAt())
                        .map(Timestamp::valueOf)
                        .orElse(null),
                findById.getId()
        );

        if (updatedRows == 0) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_USER);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int deletedRows = template.update(sql, id);

        if (deletedRows == 0) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_USER);
        }
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        int count = template.queryForObject(sql, Integer.class, email);
        return count > 0;
    }
}
