package core.pickupbackend.member.service;

import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.request.AddMemberRequest;
import core.pickupbackend.member.dto.request.UpdateMemberRequest;
import core.pickupbackend.member.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberService(final MemberRepository memberRepository, final PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member createMember(final AddMemberRequest dto) {
        return memberRepository.save(dto.toEntity(passwordEncoder));
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(final Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    public Member getMemberByEmail(final String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_EMAIL));
    }

    public Member getMemberByNickname(final String nickname) {
        return memberRepository.findByNickname(nickname).orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER));
    }

    public void deleteMemberById(final Long id) {
        memberRepository.delete(id);
    }

    public void updateMemberById(final UpdateMemberRequest dto) {
        memberRepository.update(dto.toEntity(passwordEncoder));
    }
}
