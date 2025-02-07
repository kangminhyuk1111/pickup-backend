package core.pickupbackend.match.application.in;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.dto.request.UpdateMatchRequest;

public interface UpdateMatchUseCase {
    Match update(String accessToken, Long id, UpdateMatchRequest updateMatchRequest);
}
