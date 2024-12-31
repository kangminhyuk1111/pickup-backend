package core.pickupbackend.member.domain;

import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ValidateException;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import core.pickupbackend.member.domain.vo.Password;
import core.pickupbackend.member.dto.AddMemberRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberTest {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void 생성자로_멤버_생성_테스트() {
        final Member member = new Member(
                "example@email.com",
                new Password(passwordEncoder, "encodedPassword"),
                "농구왕",
                180,
                75,
                Position.PG,
                Level.BEGINNER
        );

        assertThat(member).isNotNull();
    }

    @Test
    void 생성자로_멤버_생성_테스트시_잘못된_값_입력() {
        final AddMemberRequestDto validFailByEmailEmptyRequest = new AddMemberRequestDto("", "encodedPassword", "농구왕", 180, 75, Position.PG, Level.BEGINNER);
        assertThrows(ValidateException.class, () -> validFailByEmailEmptyRequest.toEntity(passwordEncoder));

        final AddMemberRequestDto validFailByPasswordEmptyRequest = new AddMemberRequestDto("example@email.com", "", "농구왕", 180, 75, Position.PG, Level.BEGINNER);
        assertThrows(ValidateException.class, () -> validFailByPasswordEmptyRequest.toEntity(passwordEncoder));

        final AddMemberRequestDto validFailByNicknameEmptyRequest = new AddMemberRequestDto("example@email.com", "encodedPassword", "", 180, 75, Position.PG, Level.BEGINNER);
        assertThrows(ValidateException.class, () -> validFailByNicknameEmptyRequest.toEntity(passwordEncoder));
    }

    @Test
    void ID가_포함된_생성자로_멤버_생성() {
        // given
        Password password = new Password(passwordEncoder, "password123");

        // when
        Member member = new Member(1L, "test@email.com", password, "nickname", 180, 75, Position.PG, Level.BEGINNER);

        // then
        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getEmail()).isEqualTo("test@email.com");
        assertThat(member.getPassword()).isEqualTo(password.getPassword());
        assertThat(member.getNickname()).isEqualTo("nickname");
        assertThat(member.getHeight()).isEqualTo(180);
        assertThat(member.getWeight()).isEqualTo(75);
        assertThat(member.getPosition()).isEqualTo(Position.PG);
        assertThat(member.getLevel()).isEqualTo(Level.BEGINNER);
        assertThat(member.getMannerScore()).isEqualTo(5.0);
        assertThat(member.getCreatedAt()).isNotNull();
        assertThat(member.getUpdatedAt()).isNotNull();
    }

    @Test
    void ID가_없는_생성자로_멤버_생성() {
        // given
        Password password = new Password(passwordEncoder, "password123");

        // when
        Member member = new Member("test@email.com", password, "nickname", 180, 75, Position.PG, Level.BEGINNER);

        // then
        assertThat(member.getId()).isNull();
        assertThat(member.getEmail()).isEqualTo("test@email.com");
        assertThat(member.getPassword()).isEqualTo(password.getPassword());
        assertThat(member.getNickname()).isEqualTo("nickname");
        assertThat(member.getHeight()).isEqualTo(180);
        assertThat(member.getWeight()).isEqualTo(75);
        assertThat(member.getPosition()).isEqualTo(Position.PG);
        assertThat(member.getLevel()).isEqualTo(Level.BEGINNER);
        assertThat(member.getMannerScore()).isEqualTo(5.0);
    }

    @Test
    void 이메일이_null이면_예외발생() {
        Password password = new Password(passwordEncoder, "password123");

        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Member(null, password, "nickname", 180, 75, Position.PG, Level.BEGINNER)
        );

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BLANK_EXCEPTION);
    }

    @Test
    void 이메일이_빈문자열이면_예외발생() {
        Password password = new Password(passwordEncoder, "password123");

        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Member("", password, "nickname", 180, 75, Position.PG, Level.BEGINNER)
        );

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BLANK_EXCEPTION);
    }

    @Test
    void 이메일_형식이_잘못되면_예외발생() {
        Password password = new Password(passwordEncoder, "password123");

        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Member("invalid-email", password, "nickname", 180, 75, Position.PG, Level.BEGINNER)
        );

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.EMAIL_PATTERN_EXCEPTION);
    }

    @Test
    void 닉네임이_null이면_예외발생() {
        Password password = new Password(passwordEncoder, "password123");

        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Member("test@email.com", password, null, 180, 75, Position.PG, Level.BEGINNER)
        );

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BLANK_EXCEPTION);
    }

    @Test
    void 닉네임이_빈문자열이면_예외발생() {
        Password password = new Password(passwordEncoder, "password123");

        ValidateException exception = assertThrows(
                ValidateException.class,
                () -> new Member("test@email.com", password, "", 180, 75, Position.PG, Level.BEGINNER)
        );

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BLANK_EXCEPTION);
    }

    @Test
    void setter_메서드_테스트() {
        // given
        Password password = new Password(passwordEncoder, "password123");
        Member member = new Member("test@email.com", password, "nickname", 180, 75, Position.PG, Level.BEGINNER);
        LocalDateTime now = LocalDateTime.now();

        // when
        member.setId(1L);
        member.setMannerScore(4.5);
        member.setProfileImage("profile.jpg");
        member.setUpdatedAt(now);
        member.setCreatedAt(now);
        member.setLastLoginAt(now);

        // then
        assertThat(member.getId()).isEqualTo(1L);
        assertThat(member.getMannerScore()).isEqualTo(4.5);
        assertThat(member.getProfileImage()).isEqualTo("profile.jpg");
        assertThat(member.getUpdatedAt()).isEqualTo(now);
        assertThat(member.getCreatedAt()).isEqualTo(now);
        assertThat(member.getLastLoginAt()).isEqualTo(now);
    }

    @Test
    void getter_메서드_테스트() {
        // given
        Password password = new Password(passwordEncoder, "password123");
        Member member = new Member("test@email.com", password, "nickname", 180, 75, Position.PG, Level.BEGINNER);

        // then
        assertThat(member.getEmail()).isEqualTo("test@email.com");
        assertThat(member.getPassword()).isEqualTo(password.getPassword());
        assertThat(member.getNickname()).isEqualTo("nickname");
        assertThat(member.getHeight()).isEqualTo(180);
        assertThat(member.getWeight()).isEqualTo(75);
        assertThat(member.getPosition()).isEqualTo(Position.PG);
        assertThat(member.getLevel()).isEqualTo(Level.BEGINNER);
        assertThat(member.getProfileImage()).isNull();
        assertThat(member.getLastLoginAt()).isNull();
    }
}