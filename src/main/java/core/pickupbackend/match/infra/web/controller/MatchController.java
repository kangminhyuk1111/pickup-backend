package core.pickupbackend.match.infra.web.controller;

import core.pickupbackend.global.common.code.StatusCode;
import core.pickupbackend.global.common.response.BaseResponse;
import core.pickupbackend.match.application.in.MatchService;
import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.dto.request.CreateParticipationRequest;
import core.pickupbackend.match.dto.request.UpdateMatchRequest;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.dto.request.CreateMatchRequest;
import core.pickupbackend.match.dto.request.UpdateParticipationRequest;
import core.pickupbackend.match.dto.response.*;
import core.pickupbackend.match.application.service.DefaultMatchService;
import core.pickupbackend.match.application.service.DefaultParticipationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "매치 API")
@RestController
@RequestMapping("/matches")
public class MatchController {

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final MatchService matchService;
    private final DefaultParticipationService participationService;

    public MatchController(final DefaultMatchService matchService, final DefaultParticipationService participationService) {
        this.participationService = participationService;
        this.matchService = matchService;
    }

    @Operation(summary = "매치 생성", security = { @SecurityRequirement(name = "bearerAuth") })
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public MatchResponse createMatch(@RequestBody final CreateMatchRequest createMatchDto, @RequestHeader("Authorization") final String accessToken) {
        logger.debug("/createMatch request: {}", createMatchDto.toString());
        final String token = accessToken.replace("Bearer ", "");
        final Match match = matchService.create(token, createMatchDto);
        return MatchResponse.from(match);
    }

    @Operation(summary = "매치 정보 전체 조회")
    @GetMapping
    @ResponseBody
    public MatchesPagingResponse findAllMatches(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        logger.debug("/findAllMatches request");
        return matchService.findAll(page, size);
    }

    @Operation(summary = "매치 정보 단건 조회")
    @GetMapping("/{id}")
    @ResponseBody
    public MatchResponse getMatchById(@PathVariable("id") final Long id) {
        logger.debug("/getMatchById request: {}", id);
        final Match match = matchService.findById(id);
        return MatchResponse.from(match);
    }

    @Operation(summary = "매치 정보 업데이트", security = { @SecurityRequirement(name = "bearerAuth") })
    @PutMapping("/{id}")
    @ResponseBody
    public MatchResponse updateMatch(@PathVariable("id") final Long id, @RequestBody final UpdateMatchRequest updateMatchDto, @RequestHeader("Authorization") final String accessToken) {
        logger.debug("/updateMatch request: {}", updateMatchDto.toString());
        final String token = accessToken.replace("Bearer ", "");
        final Match match = matchService.update(token, id, updateMatchDto);
        return MatchResponse.from(match);
    }

    @Operation(summary = "매치 삭제", security = { @SecurityRequirement(name = "bearerAuth") })
    @DeleteMapping("/{matchId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatch(@RequestHeader("Authorization") final String accessToken, @PathVariable("matchId") final Long matchId) {
        logger.debug("/deleteMatch request: {}", matchId);
        final String token = accessToken.replace("Bearer ", "");
        matchService.deleteById(token, matchId);
    }

    @Operation(summary = "매치 참여 신청", security = { @SecurityRequirement(name = "bearerAuth") })
    @PostMapping("/participation")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationResponse addParticipation(@RequestHeader("Authorization") final String accessToken, @RequestBody final CreateParticipationRequest createParticipationDto) {
        logger.debug("/addParticipation request: {}", createParticipationDto.toString());
        final String token = accessToken.replace("Bearer ", "");
        final Participation participation = participationService.createParticipation(token, createParticipationDto);
        return ParticipationResponse.from(participation);
    }

    @Operation(summary = "매치와 매치 참여자의 정보를 조회", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping("/participation")
    @ResponseBody
    public BaseResponse<List<MatchParticipationResponse>> getParticipation(@RequestHeader("Authorization") final String accessToken) {
        logger.debug("/getParticipation request: {}", accessToken);
        final String token = accessToken.replace("Bearer ", "");
        List<MatchParticipationResponse> responses = matchService.findMatchParticipationByMemberId(token);
        return new BaseResponse<>(StatusCode.SUCCESS, responses);
    }

    @Operation(summary = "매치 참여자 상태 수락", security = { @SecurityRequirement(name = "bearerAuth") })
    @PostMapping("/status")
    @ResponseBody
    public ParticipationResponse updateParticipationStatus(@RequestBody final UpdateParticipationRequest updateParticipationRequest) {
        logger.debug("/status request: {}", updateParticipationRequest);
        final Participation participation = matchService.updateStatus(updateParticipationRequest);
        return ParticipationResponse.from(participation);
    }

    @Operation(summary = "위치 필터 리스트", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping("/districts")
    @ResponseBody
    public List<String> districts(@RequestHeader("Authorization") final String accessToken) {
        logger.debug("/filter request: {}", accessToken);
        return matchService.findAlldistricts();
    }
}
