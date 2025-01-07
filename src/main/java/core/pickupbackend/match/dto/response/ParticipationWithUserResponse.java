package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Participation;

public class ParticipationWithUserResponse {

    private ParticipationMemberResponse member;
    private Participation participation;

    public ParticipationWithUserResponse() {
    }

    public ParticipationWithUserResponse(final ParticipationMemberResponse member, final Participation participation) {
        this.member = member;
        this.participation = participation;
    }

    public ParticipationMemberResponse getMember() {
        return member;
    }

    public Participation getParticipation() {
        return participation;
    }

    @Override
    public String toString() {
        return "ParticipationWithUserResponse{" +
                "ParticipationMemberResponse=" + member +
                ", participation=" + participation +
                '}';
    }
}
