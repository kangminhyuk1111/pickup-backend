package core.pickupbackend.member.application.in;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.request.UpdateMemberRequest;

public interface UpdateByIdUseCase {
    Member updateMember(UpdateMemberRequest dto);
}
