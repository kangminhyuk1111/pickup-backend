package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Match;

import java.util.List;

public record MatchesPagingResponse(List<MatchResponse> matchResponses, Integer currentPage, Integer totalPages) {
    public static MatchesPagingResponse from(List<Match> matches, Integer currentPage, Integer totalPages) {
        final List<MatchResponse> matchesResponse = matches.stream().map(MatchResponse::from).toList();
        return new MatchesPagingResponse(matchesResponse, currentPage, totalPages);
    }
}
