package core.pickupbackend.member.infra.web.controller;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.common.code.StatusCode;
import core.pickupbackend.global.common.response.BaseResponse;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.dto.request.AddMemberRequest;
import core.pickupbackend.member.dto.request.UpdateMemberRequest;
import core.pickupbackend.member.application.service.DefaultMemberService;
import core.pickupbackend.member.dto.response.MemberResponse;
import core.pickupbackend.member.dto.response.MemberUpdateResponse;
import core.pickupbackend.member.dto.response.MembersResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "사용자 API")
@RestController
@RequestMapping("/member")
public class MemberController {

    private final static Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final DefaultMemberService memberService;
    private final TokenProvider tokenProvider;

    public MemberController(final DefaultMemberService memberService, final TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
    }

    @Operation(summary = "전체 회원 조회", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping
    public ResponseEntity<MembersResponse> getMembers() {
        logger.info("/member request");
        final List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(MembersResponse.from(members));
    }

    @Operation(summary = "회원 가입")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MemberResponse> signUp(@RequestBody AddMemberRequest addMemberRequestDto) {
        logger.info("/member request: {}", addMemberRequestDto.toString());
        final Member member = memberService.createMember(addMemberRequestDto);
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    @Operation(summary = "ID 기준 회원 정보 조회", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id) {
        logger.info("/member/:id request: {}", id);
        final Member member = memberService.getMemberById(id);
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    @Operation(summary = "마이 페이지 유저 정보 조회", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping("/mypage")
    public ResponseEntity<MemberResponse> getMemberByEmail(@RequestHeader("Authorization") String accessToken) {
        logger.info("/member/mypage request: {}", accessToken);
        final String token = accessToken.replace("Bearer ", "");
        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);
        return ResponseEntity.ok(MemberResponse.from(member));
    }
    
    @Operation(summary = "유저 정보 삭제", security = { @SecurityRequirement(name = "bearerAuth") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable Long id) {
        logger.info("/member/:id request: {}", id);
        memberService.deleteMemberById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "유저 정보 업데이트", security = { @SecurityRequirement(name = "bearerAuth") })
    @PutMapping()
    public ResponseEntity<MemberUpdateResponse> updateMemberById(@RequestBody UpdateMemberRequest dto) {
        logger.info("/member update request: {}", dto);
        Member updatedMember = memberService.updateMemberById(dto);
        return ResponseEntity.ok(MemberUpdateResponse.from(updatedMember));
    }
}
