package core.pickupbackend.member.application.service;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.application.in.MemberService;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.request.AddMemberRequest;
import core.pickupbackend.member.dto.request.UpdateMemberRequest;
import core.pickupbackend.member.application.out.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultMemberService implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMemberService.class);

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public DefaultMemberService(final MemberRepository memberRepository, final PasswordEncoder passwordEncoder, final TokenProvider tokenProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Transactional
    @Override
    public Member createMember(final AddMemberRequest dto) {
        return memberRepository.save(dto.toEntity(passwordEncoder));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(final Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    public Member getMemberByEmail(final String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_EMAIL));
    }

    public Member findMemberByToken(final String token) {
        final String email = tokenProvider.extractEmailFromToken(token);
        return memberRepository.findByEmail(email).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    public Member getMemberByNickname(final String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    @Transactional
    public void deleteMemberById(final Long id) {
        memberRepository.delete(id);
    }

    @Transactional
    public void updateMemberById(final UpdateMemberRequest dto) {
        Member existingMember = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));

        Member updatedMember = dto.toEntity(existingMember.getId(),passwordEncoder);

        memberRepository.update(updatedMember);
    }
}
