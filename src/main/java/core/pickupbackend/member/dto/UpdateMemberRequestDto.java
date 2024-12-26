package core.pickupbackend.member.dto;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;

public class UpdateMemberRequestDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private Integer height;
    private Integer weight;
    private Position position;
    private Level level;

    // 생성자
    public UpdateMemberRequestDto(Long id, String email, String password, String nickname,
                                  Integer height, Integer weight, Position position, Level level) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.level = level;
    }

    public Member toEntity() {
        return new Member(id, email, password, nickname, height, weight, position, level);
    }
}
