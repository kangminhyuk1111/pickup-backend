package core.pickupbackend.member.domain.type;

public enum Level {
    BEGINNER("초급"),
    INTERMEDIATE("중급"),
    ADVANCED("상급");

    private final String level;

    Level(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}