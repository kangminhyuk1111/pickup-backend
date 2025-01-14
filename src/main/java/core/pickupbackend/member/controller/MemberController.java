package core.pickupbackend.member.controller;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.common.code.StatusCode;
import core.pickupbackend.global.common.response.BaseResponse;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.request.AddMemberRequest;
import core.pickupbackend.member.dto.request.UpdateMemberRequest;
import core.pickupbackend.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "사용자 API")
@RestController
@RequestMapping("/member")
public class MemberController {

    private final static Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    public MemberController(final MemberService memberService, final TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
    }

    @Operation(summary = "전체 회원 조회")
    @GetMapping
    public BaseResponse<List<Member>> getMembers() {
        logger.info("/member request");
        final List<Member> members = memberService.getAllMembers();
        return new BaseResponse<>(StatusCode.SUCCESS, members);
    }

    @Operation(summary = "회원 가입")
    @PostMapping
    public BaseResponse<Member> signUp(@RequestBody AddMemberRequest addMemberRequestDto) {
        logger.info("/member request: {}", addMemberRequestDto.toString());
        final Member member = memberService.createMember(addMemberRequestDto);
        return new BaseResponse<>(StatusCode.SUCCESS, member);
    }

    @Operation(summary = "ID 기준 회원 정보 조회")
    @GetMapping("/{id}")
    public BaseResponse<Member> getMemberById(@PathVariable Long id) {
        logger.info("/member/:id request: {}", id);
        final Member member = memberService.getMemberById(id);
        return new BaseResponse<>(StatusCode.SUCCESS, member);
    }

    @Operation(summary = "마이 페이지 유저 정보 조회")
    @GetMapping("/mypage")
    public BaseResponse<Member> getMemberByEmail(@RequestHeader("Authorization") String accessToken) {
        logger.info("/member/mypage request: {}", accessToken);
        final String token = accessToken.replace("Bearer ", "");
        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);
        return new BaseResponse<>(StatusCode.SUCCESS, member);
    }
    
    @Operation(summary = "유저 정보 삭제")
    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteMemberById(@PathVariable Long id) {
        logger.info("/member/:id request: {}", id);
        memberService.deleteMemberById(id);
        return new BaseResponse(StatusCode.SUCCESS, null);
    }

    @Operation(summary = "유저 정보 업데이트")
    @PatchMapping()
    public BaseResponse<Void> updateMemberById(@RequestBody UpdateMemberRequest dto) {
        logger.info("/member request: {}", dto.toString());
        memberService.updateMemberById(dto);
        return new BaseResponse(StatusCode.SUCCESS, null);
    }
}
