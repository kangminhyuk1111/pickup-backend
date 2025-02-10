package core.pickupbackend.member.dto.request;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import core.pickupbackend.member.domain.vo.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.security.crypto.password.PasswordEncoder;

public record UpdateMemberRequest(
        @Schema(description = "이메일", example = "test@gmail.com")
        String email,

        @Schema(description = "비밀번호", example = "test1234@")
        String password,

        @Schema(description = "닉네임", example = "농구왕")
        String nickname,

        @Schema(description = "키(cm)", example = "180")
        Integer height,

        @Schema(description = "몸무게(kg)", example = "75")
        Integer weight,

        @Schema(description = "포지션", example = "CENTER")
        Position position,

        @Schema(description = "실력 레벨", example = "BEGINNER")
        Level level
) {
    public Member toEntity(final Long id, final PasswordEncoder passwordEncoder) {
        return new Member(id, email, new Password(passwordEncoder, password), nickname, height, weight, position, level);
    }
}