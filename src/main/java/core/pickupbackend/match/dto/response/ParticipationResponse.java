package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.domain.ParticipationStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ParticipationResponse(
        Long id,
        Long userId,
        Long matchingId,
        ParticipationStatus status,
        String message,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ParticipationResponse from(Participation participation) {
        return new ParticipationResponse(
                participation.getId(),
                participation.getUserId(),
                participation.getMatchingId(),
                participation.getStatus(),
                participation.getMessage(),
                participation.getCreatedAt(),
                participation.getUpdatedAt()
        );
    }

    public static List<ParticipationResponse> from(List<Participation> participations) {
        return participations.stream()
                .map(ParticipationResponse::from)
                .toList();
    }
}