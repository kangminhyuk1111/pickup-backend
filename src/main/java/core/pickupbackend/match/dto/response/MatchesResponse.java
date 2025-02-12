package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Match;

import java.util.List;

public record MatchesResponse(List<MatchResponse> matchResponses) {
    public static MatchesResponse from(List<Match> matches) {
        final List<MatchResponse> matchesResponse = matches.stream().map(MatchResponse::from).toList();
        return new MatchesResponse(matchesResponse);
    }
}
