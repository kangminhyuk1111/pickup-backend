package core.pickupbackend.member.domain;

import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;

import java.time.LocalDateTime;

public class Member {

    public static final double DEFAULT_MANNER_SCORE = 5.0;

    private Long id;                    // Primary Key
    private String email;               // 이메일 (Unique)
    private String password;            // 비밀번호
    private String nickname;            // 닉네임
    private String profileImage;        // 프로필 이미지 URL
    private Integer height;             // 키
    private Integer weight;             // 몸무게
    private Position position;          // 선호 포지션
    private Level level;                // 실력 수준
    private Double mannerScore;         // 매너 점수
    private LocalDateTime createdAt;    // 생성 일시
    private LocalDateTime updatedAt;    // 수정 일시
    private LocalDateTime lastLoginAt;  // 마지막 로그인 일시

    public Member(String email, String password, String nickname, String profileImage,
                  Integer height, Integer weight, Position position, Level level) {

        validateEmail(email);
        validatePassword(password);
        validateNickname(nickname);

        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.level = level;
        this.mannerScore = DEFAULT_MANNER_SCORE;  // 초기 매너 점수
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private void validateEmail(final String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
    }

    private void validatePassword(final String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

    private void validateNickname(final String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("Nickname cannot be null or empty");
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }
}
