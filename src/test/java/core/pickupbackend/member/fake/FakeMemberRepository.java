package core.pickupbackend.member.fake;

import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.repository.MemberRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class FakeMemberRepository implements MemberRepository {
    private final Map<Long, Member> store = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Member save(Member member) {
        if (findByEmail(member.getEmail()).isPresent()) {
            throw new ApplicationException(ErrorCode.EMAIL_EXIST);
        }

        if (findByNickname(member.getNickname()).isPresent()) {
            throw new ApplicationException(ErrorCode.NICKNAME_EXIST);
        }

        Long id = sequence.getAndIncrement();
        Member savedMember = new Member(
                id,
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getHeight(),
                member.getWeight(),
                member.getPosition(),
                member.getLevel()
        );
        store.put(id, savedMember);
        return savedMember;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return store.values().stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<Member> findByNickname(String nickname) {
        return store.values().stream()
                .filter(member -> member.getNickname().equals(nickname))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void update(Member member) {
        store.put(member.getId(), member);
    }

    public void clearStore() {
        store.clear();
        sequence.set(1L);
    }
}
