package core.pickupbackend.member.application.in;

import core.pickupbackend.member.domain.Member;

import java.util.List;

public interface GetAllMemberUseCase {
    List<Member> getAllMembers();
}
