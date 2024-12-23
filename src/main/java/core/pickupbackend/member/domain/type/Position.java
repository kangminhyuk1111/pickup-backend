package core.pickupbackend.member.domain.type;

public enum Position {
    PG("Point Guard"),
    SG("Shooting Guard"),
    SF("Small Forward"),
    PF("Power Forward"),
    C("Center");

    private final String position;

    Position(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}