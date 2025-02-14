package core.pickupbackend.match.application.in;

import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.dto.request.CreateParticipationRequest;

public interface CreateParticipationUseCase {
    Participation createParticipation(String token, CreateParticipationRequest createParticipationRequest);
}
