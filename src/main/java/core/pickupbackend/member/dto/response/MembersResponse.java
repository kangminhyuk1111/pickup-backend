package core.pickupbackend.member.dto.response;

import core.pickupbackend.member.domain.Member;

import java.util.List;

public record MembersResponse(List<Member> members) {
    public static MembersResponse from(List<Member> members) {
        return new MembersResponse(members);
    }
}
