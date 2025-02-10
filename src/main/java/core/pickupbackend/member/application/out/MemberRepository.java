package core.pickupbackend.member.application.out;

import core.pickupbackend.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    List<Member> findAll();

    Member update(Member member);

    void delete(Long id);
}
