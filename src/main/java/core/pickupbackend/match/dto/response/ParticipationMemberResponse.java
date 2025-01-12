package core.pickupbackend.match.dto.response;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;

public class ParticipationMemberResponse {
    private String email;
    private String nickname;
    private String profileImage;
    private Integer height;
    private Integer weight;
    private Position position;
    private Level level;
    private Double mannerScore;

    public ParticipationMemberResponse(Member member) {
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.profileImage = member.getProfileImage();
        this.height = member.getHeight();
        this.weight = member.getWeight();
        this.position = member.getPosition();
        this.level = member.getLevel();
        this.mannerScore = member.getMannerScore();
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Position getPosition() {
        return position;
    }

    public Level getLevel() {
        return level;
    }

    public Double getMannerScore() {
        return mannerScore;
    }

    @Override
    public String toString() {
        return "MemberResponse{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", position='" + position + '\'' +
                ", level='" + level + '\'' +
                ", mannerScore=" + mannerScore +
                '}';
    }
}