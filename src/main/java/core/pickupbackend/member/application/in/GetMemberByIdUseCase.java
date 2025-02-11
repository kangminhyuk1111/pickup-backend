package core.pickupbackend.member.application.in;

import core.pickupbackend.member.domain.Member;

public interface GetMemberByIdUseCase {
    Member getMemberById(Long id);
}
