package core.pickupbackend.member.application.in;

import core.pickupbackend.member.domain.Member;

public interface GetMemberByEmailUseCase {
    Member getMemberByEmail(String email);
}
