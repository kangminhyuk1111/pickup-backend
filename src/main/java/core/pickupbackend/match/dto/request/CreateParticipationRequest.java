package core.pickupbackend.match.dto.request;

import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.domain.ParticipationStatus;

public class CreateParticipationRequest {
    private Long matchingId;
    private ParticipationStatus status;
    private String message;

    public CreateParticipationRequest() {
    }

    public Long getMatchingId() {
        return matchingId;
    }

    public ParticipationStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Participation toEntity(Long memberId) {
        return new Participation(memberId, matchingId , status, message);
    }

    @Override
    public String toString() {
        return "CreateParticipationRequest{" +
                "matchingId=" + matchingId +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
