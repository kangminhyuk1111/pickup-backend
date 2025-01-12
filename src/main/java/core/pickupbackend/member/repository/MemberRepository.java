package core.pickupbackend.member.repository;

import core.pickupbackend.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    List<Member> findAll();

    void update(Member member);

    void delete(Long id);
}
