package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Match;

import java.util.List;

public class MatchParticipationResponse {
    private Match match;
    private List<ParticipationWithUserResponse> participations;

    public MatchParticipationResponse() {
    }

    public MatchParticipationResponse(final Match match, final List<ParticipationWithUserResponse> participations) {
        this.match = match;
        this.participations = participations;
    }

    public Match getMatch() {
        return match;
    }

    public List<ParticipationWithUserResponse> getParticipations() {
        return participations;
    }
}
