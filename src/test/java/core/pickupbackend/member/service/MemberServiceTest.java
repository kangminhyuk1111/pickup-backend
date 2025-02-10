package core.pickupbackend.member.service;

import core.pickupbackend.auth.fake.FakeKeyProvider;
import core.pickupbackend.auth.provider.JjwtTokenProvider;
import core.pickupbackend.auth.provider.KeyProvider;
import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ValidateException;
import core.pickupbackend.member.application.service.DefaultMemberService;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.domain.type.Level;
import core.pickupbackend.member.domain.type.Position;
import core.pickupbackend.member.dto.request.AddMemberRequest;
import core.pickupbackend.member.fake.FakeMemberRepository;
import core.pickupbackend.member.application.out.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberServiceTest {

    private final MemberRepository memberRepository = new FakeMemberRepository();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final KeyProvider keyProvider = new FakeKeyProvider();
    private final TokenProvider tokenProvider = new JjwtTokenProvider(keyProvider);
    private final DefaultMemberService memberService = new DefaultMemberService(memberRepository, passwordEncoder, tokenProvider);

    @Test
    void 멤버_생성_테스트() {
        final String email = "kangminhyuk1111@gmail.com";
        final String password = "rkdglqkr12@";
        final String nickname = "kangminhyuk";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);

        final Member member = memberService.createMember(createMemberRequest);

        assertThat(member).isNotNull();
        assertThat(member.getEmail()).isEqualTo(email);
    }

    @Test
    void 멤버_생성_실패_이미_생성된_이메일() {
        final String email = "kangminhyuk1111@gmail.com";
        final String password = "rkdglqkr12@";
        final String nickname = "kangminhyuk";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);

        memberService.createMember(createMemberRequest);

        assertThatThrownBy(() -> memberService.createMember(createMemberRequest)).isInstanceOf(ApplicationException.class);
    }

    @Test
    void 멤버_생성_실패_이미_존재하는_닉네임() {
        final String email = "kangminhyuk1111@gmail.com";
        final String notExistEmail = "test1234@gmail.com";
        final String password = "rkdglqkr12@";
        final String nickname = "kangminhyuk";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);
        final AddMemberRequest existNicknameRequest = new AddMemberRequest(notExistEmail, password, nickname, height, weight, position, level);

        memberService.createMember(createMemberRequest);

        assertThatThrownBy(() -> memberService.createMember(existNicknameRequest)).isInstanceOf(ApplicationException.class);
    }

    @Test
    void 멤버_생성_실패_이메일이_올바른_형식이_아님() {
        final String email = "kangminhyuk1111";
        final String password = "rkdglqkr12@";
        final String nickname = "kangminhyuk";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);

        assertThatThrownBy(() -> memberService.createMember(createMemberRequest)).isInstanceOf(ValidateException.class);
    }

    @Test
    void 멤버_생성_실패_비밀번호가_올바른_형식이_아님() {
        final String email = "kangminhyuk1111@gmail.com";
        final String password = "11";
        final String nickname = "kangminhyuk";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);

        assertThatThrownBy(() -> memberService.createMember(createMemberRequest)).isInstanceOf(ValidateException.class);
    }

    @Test
    void 멤버_생성후_이메일_기준으로_멤버_찾기() {
        final String email = "kangminhyuk1111@gmail.com";
        final String password = "rkdglqkr12@";
        final String nickname = "kangminhyuk";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);

        final Member createdMember = memberService.createMember(createMemberRequest);

        final Member getMemberByEmail = memberService.getMemberByEmail(email);

        assertThat(getMemberByEmail).isNotNull();
        assertThat(getMemberByEmail).isEqualTo(createdMember);
    }

    @Test
    void 멤버_생성후_닉네임_기준으로_멤버_찾기() {
        final String email = "kangminhyuk1111@gmail.com";
        final String password = "rkdglqkr12@";
        final String nickname = "kangminhyuk";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);

        final Member createdMember = memberService.createMember(createMemberRequest);

        final Member getMemberByNickname = memberService.getMemberByNickname(nickname);

        assertThat(getMemberByNickname).isNotNull();
        assertThat(getMemberByNickname).isEqualTo(createdMember);
    }

    @Test
    void 이메일_기준으로_찾았을때_없는_이메일이라면_에러처리() {
        final String email = "kangminhyuk1111@gmail.com";
        final String notExistEmail = "test1234@gmail.com";
        final String password = "rkdglqkr12@";
        final String nickname = "kangminhyuk";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);

        memberService.createMember(createMemberRequest);

        assertThatThrownBy(() -> memberService.getMemberByEmail(notExistEmail)).isInstanceOf(ApplicationException.class);
    }

    @Test
    void 닉네임_기준으로_찾았을때_없는_닉네임이라면_에러처리() {
        final String email = "kangminhyuk1111@gmail.com";
        final String password = "rkdglqkr12@";
        final String nickname = "kangminhyuk";
        final String notExistNickname = "testname";
        final int height = 188;
        final int weight = 90;
        final Position position = Position.SF;
        final Level level = Level.BEGINNER;
        final AddMemberRequest createMemberRequest = new AddMemberRequest(email, password, nickname, height, weight, position, level);

        memberService.createMember(createMemberRequest);

        assertThatThrownBy(() -> memberService.getMemberByNickname(notExistNickname)).isInstanceOf(ApplicationException.class);
    }
}