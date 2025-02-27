package core.pickupbackend.member.infra.repository;

import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.application.out.MemberRepository;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.mapper.MemberRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemberRepository implements MemberRepository {

    private static final Logger logger = LoggerFactory.getLogger(JdbcMemberRepository.class);

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

        template.update(sql,
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getProfileImage(),
                member.getHeight(),
                member.getWeight(),
                member.getPosition().name(),
                member.getLevel().name(),
                member.getMannerScore(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );

        return findByEmail(member.getEmail())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return template.query(sql, rowMapper, id).stream().findAny();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return template.query(sql, rowMapper, email).stream().findAny();
    }

    @Override
    public Optional<Member> findByNickname(final String nickname) {
        String sql = "SELECT * FROM users WHERE nickname = ?";
        return template.query(sql, rowMapper, nickname).stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM users";
        return template.query(sql, rowMapper);
    }

    @Override
    public Member update(final Member member) {
        String sql = """
                UPDATE users 
                SET email = ?, password = ?, nickname = ?, profile_image = ?,
                    height = ?, weight = ?, position = ?, level = ?, manner_score = ?,
                    updated_at = ?, last_login_at = ? 
                WHERE id = ?
                """;

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
                member.getUpdatedAt(),
                member.getLastLoginAt(),
                member.getId()
        );

        if (updatedRows == 0) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_USER);
        }

        return findById(member.getId()).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        int deletedRows = template.update(sql, id);

        if (deletedRows == 0) {
            throw new IllegalStateException("Failed to delete member with id: " + id);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = template.queryForObject(sql, Integer.class, email);
        return count > 0;
    }
}
