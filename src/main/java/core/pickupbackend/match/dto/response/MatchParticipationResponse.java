package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.Participation;

import java.util.List;

public class MatchParticipationResponse {
    private Match match;
    private List<Participation> participations;

    public MatchParticipationResponse(final Match match, final List<Participation> participations) {
        this.match = match;
        this.participations = participations;
    }

    public Match getMatch() {
        return match;
    }

    public List<Participation> getParticipations() {
        return participations;
    }
}
