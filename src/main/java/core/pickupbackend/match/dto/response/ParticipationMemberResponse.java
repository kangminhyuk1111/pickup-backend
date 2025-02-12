package core.pickupbackend.match.dto.response;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;

public record ParticipationMemberResponse(
        String email,
        String nickname,
        String profileImage,
        Integer height,
        Integer weight,
        Position position,
        Level level,
        Double mannerScore
) {
    public static ParticipationMemberResponse from(Member member) {
        return new ParticipationMemberResponse(
                member.getEmail(),
                member.getNickname(),
                member.getProfileImage(),
                member.getHeight(),
                member.getWeight(),
                member.getPosition(),
                member.getLevel(),
                member.getMannerScore()
        );
    }
}