package core.pickupbackend.member.dto.request;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import core.pickupbackend.member.domain.vo.Password;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AddMemberRequest {
    private String email;
    private String password;
    private String nickname;
    private Integer height;
    private Integer weight;
    private Position position;
    private Level level;

    // 생성자
    public AddMemberRequest(String email, String password, String nickname,
                            Integer height, Integer weight, Position position, Level level) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.level = level;
    }

    public Member toEntity(final PasswordEncoder passwordEncoder) {
        return new Member(email, new Password(passwordEncoder, password), nickname, height, weight, position, level);
    }
}
