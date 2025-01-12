package core.pickupbackend.match.domain;

public enum MatchStatus {
    OPEN("모집 중"),
    CLOSED("마감");

    private final String value;

    MatchStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
