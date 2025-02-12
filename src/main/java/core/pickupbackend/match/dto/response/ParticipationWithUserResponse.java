package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Participation;

public record ParticipationWithUserResponse(
        ParticipationMemberResponse member,
        ParticipationResponse participation
) {
    public static ParticipationWithUserResponse of(
            ParticipationMemberResponse member,
            Participation participation
    ) {
        return new ParticipationWithUserResponse(
                member,
                ParticipationResponse.from(participation)
        );
    }
}