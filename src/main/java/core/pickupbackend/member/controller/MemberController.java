package core.pickupbackend.member.controller;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.request.AddMemberRequest;
import core.pickupbackend.member.dto.request.UpdateMemberRequest;
import core.pickupbackend.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    public MemberController(final MemberService memberService, final TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping
    public Member signUp(@RequestBody AddMemberRequest addMemberRequestDto) {
        return memberService.createMember(addMemberRequestDto);
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/mypage")
    public Member getMemberByEmail(@RequestHeader("Authorization") String accessToken) {
        final String token = accessToken.replace("Bearer ", "");
        final String email = tokenProvider.extractEmailFromToken(token);
        return memberService.getMemberByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
    }

    @PatchMapping()
    public void updateMemberById(@RequestBody UpdateMemberRequest dto) {
        memberService.updateMemberById(dto);
    }
}
