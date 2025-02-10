package core.pickupbackend.member.application.in;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.request.AddMemberRequest;

public interface CreateMemberUseCase {
    Member createMember(AddMemberRequest member);
}
