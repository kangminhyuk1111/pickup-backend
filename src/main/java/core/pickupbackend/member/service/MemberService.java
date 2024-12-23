package core.pickupbackend.member.service;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.AddMemberRequestDto;
import core.pickupbackend.member.repository.JdbcMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final JdbcMemberRepository memberRepository;

    public MemberService(final JdbcMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member createMember(final AddMemberRequestDto addMemberRequestDto) {
        return memberRepository.save(addMemberRequestDto.toEntity());
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
