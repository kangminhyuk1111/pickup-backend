package core.pickupbackend.match.dto.request;

import core.pickupbackend.match.domain.Participation;

public class CreateParticipationRequest {
    private Long memberId;
    private Long matchId;
    private String status;
    private String message;

    public Long getMemberId() {
        return memberId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Participation toEntity() {
        return new Participation(memberId, matchId, status, message);
    }
}
