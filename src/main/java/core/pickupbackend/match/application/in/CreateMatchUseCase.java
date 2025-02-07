package core.pickupbackend.match.application.in;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.dto.request.CreateMatchRequest;

public interface CreateMatchUseCase {
    Match create(String accessToken, CreateMatchRequest createMatchRequest);
}
