package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Match;

import java.util.List;

public record MatchParticipationResponse(
        MatchResponse match,
        List<ParticipationWithUserResponse> participations
) {
    public static MatchParticipationResponse from(Match match, List<ParticipationWithUserResponse> participations) {
        return new MatchParticipationResponse(
                MatchResponse.from(match),
                participations
        );
    }
}