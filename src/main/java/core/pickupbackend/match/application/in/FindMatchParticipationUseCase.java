package core.pickupbackend.match.application.in;

import core.pickupbackend.match.dto.response.MatchParticipationResponse;

import java.util.List;

public interface FindMatchParticipationUseCase {
    List<MatchParticipationResponse> findMatchParticipationByMemberId(String accessToken);
}
