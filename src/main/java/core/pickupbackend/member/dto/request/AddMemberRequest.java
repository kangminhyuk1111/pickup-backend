package core.pickupbackend.member.dto.request;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import core.pickupbackend.member.domain.vo.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

public record AddMemberRequest(
        String email,
        String password,
        String nickname,
        Integer height,
        Integer weight,
        Position position,
        Level level
) {
    // 검증 및 기본값 설정을 위한 컴팩트 생성자
    public AddMemberRequest {
        level = Level.BEGINNER; // 기본값 설정
    }

    public Member toEntity(final PasswordEncoder passwordEncoder) {
        return new Member(email, new Password(passwordEncoder, password), nickname, height, weight, position, level);
    }
}
