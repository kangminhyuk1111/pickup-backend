package core.pickupbackend.member.controller;

import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.AddMemberRequestDto;
import core.pickupbackend.member.dto.UpdateMemberRequestDto;
import core.pickupbackend.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping
    public Member signUp(@RequestBody AddMemberRequestDto addMemberRequestDto) {
        return memberService.createMember(addMemberRequestDto);
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        memberService.deleteMemberById(id);
    }

    @PatchMapping()
    public void updateMemberById(@RequestBody UpdateMemberRequestDto dto) {
        memberService.updateMemberById(dto);
    }
}
