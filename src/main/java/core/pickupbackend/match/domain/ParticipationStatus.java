package core.pickupbackend.match.domain;

public enum ParticipationStatus {

    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private String status;

    ParticipationStatus(final String value) {
        this.status = value;
    }

    public String getStatus() {
        return status;
    }
}
