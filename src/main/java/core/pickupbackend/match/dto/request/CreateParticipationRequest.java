package core.pickupbackend.match.dto.request;

import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.domain.ParticipationStatus;

public record CreateParticipationRequest(
        Long matchingId,
        ParticipationStatus status,
        String message
) {
    public Participation toEntity(Long memberId) {
        return new Participation(memberId, matchingId, status, message);
    }
}