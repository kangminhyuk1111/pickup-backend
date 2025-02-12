package core.pickupbackend.match.dto.request;

import core.pickupbackend.match.domain.ParticipationStatus;

public record UpdateParticipationRequest(Long participationId, ParticipationStatus status) {
}
