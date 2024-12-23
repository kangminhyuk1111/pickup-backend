package core.pickupbackend.member.domain.type;

public enum Level {
    BEGINNER("BEGINNER"),
    INTERMEDIATE("INTERMEDIATE"),
    ADVANCED("ADVANCED");

    private final String level;

    Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}