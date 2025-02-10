package core.pickupbackend.member.dto.response;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;

import java.time.LocalDateTime;

public record MemberUpdateResponse(
        Long id,
        String email,
        String nickname,
        Integer height,
        Integer weight,
        Position position,
        Level level,
        Double mannerScore,
        LocalDateTime updatedAt
) {
    public static MemberUpdateResponse from(Member member) {
        return new MemberUpdateResponse(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getHeight(),
                member.getWeight(),
                member.getPosition(),
                member.getLevel(),
                member.getMannerScore(),
                member.getUpdatedAt()
        );
    }
}