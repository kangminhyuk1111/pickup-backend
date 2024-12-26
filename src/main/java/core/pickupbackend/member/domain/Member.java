package core.pickupbackend.member.domain;

import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ValidateException;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Member implements Serializable {

    private static final double DEFAULT_MANNER_SCORE = 5.0;
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    private static final String PASSWORD_REGEX = "^.{8,16}$";

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

    public Member(Long id, String email, String password, String nickname,
                  Integer height, Integer weight, Position position, Level level) {

        validateEmail(email);
        validatePassword(password);
        validateNickname(nickname);

        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.position = position;
        this.level = level;
        this.mannerScore = DEFAULT_MANNER_SCORE;  // 초기 매너 점수
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Member(String email, String password, String nickname,
                  Integer height, Integer weight, Position position, Level level) {

        validateEmail(email);
        validatePassword(password);
        validateNickname(nickname);

        this.email = email;
        this.password = password;
        this.nickname = nickname;
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
            throw new ValidateException(ErrorCode.BLANK_EXCEPTION);
        }

        if (!EMAIL_REGEX.matcher(email).matches()) {
            throw new ValidateException(ErrorCode.EMAIL_PATTERN_EXCEPTION);
        }
    }

    private void validatePassword(final String password) {
        if (password == null || password.isBlank()) {
            throw new ValidateException(ErrorCode.BLANK_EXCEPTION);
        }

        if (!password.matches(PASSWORD_REGEX)) {
            throw new ValidateException(ErrorCode.PASSWORD_VALID_EXCEPTION);
        }
    }

    private void validateNickname(final String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new ValidateException(ErrorCode.BLANK_EXCEPTION);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public void setMannerScore(final Double mannerScore) {
        this.mannerScore = mannerScore;
    }

    public void setProfileImage(final String profileImage) {
        this.profileImage = profileImage;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastLoginAt(final LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
