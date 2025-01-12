package core.pickupbackend.member.domain.type;

public enum Position {
    PG("PG"),
    SG("SG"),
    SF("SF"),
    PF("PF"),
    C("C");

    private final String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}