package core.pickupbackend.match.controller;

import core.pickupbackend.global.common.code.StatusCode;
import core.pickupbackend.global.common.response.BaseResponse;
import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.dto.request.CreateParticipationRequest;
import core.pickupbackend.match.dto.request.UpdateMatchRequest;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.dto.request.CreateMatchRequest;
import core.pickupbackend.match.dto.response.MatchParticipationResponse;
import core.pickupbackend.match.service.MatchService;
import core.pickupbackend.match.service.ParticipationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final MatchService matchService;
    private final ParticipationService participationService;

    public MatchController(final MatchService matchService, final ParticipationService participationService) {
        logger.debug("create match controller");
        this.participationService = participationService;
        this.matchService = matchService;
    }

    @PostMapping
    @ResponseBody
    public BaseResponse<Match> createMatch(@RequestBody final CreateMatchRequest createMatchDto, @RequestHeader("Authorization") final String accessToken) {
        logger.debug("/createMatch request: {}", createMatchDto.toString());
        final String token = accessToken.replace("Bearer ", "");
        Match match = matchService.createMatch(token, createMatchDto);
        return new BaseResponse<>(StatusCode.SUCCESS, match);
    }

    @GetMapping
    @ResponseBody
    public BaseResponse<List<Match>> getMatches() {
        logger.debug("/getMatches request");
        List<Match> matches = matchService.findAll();
        return new BaseResponse<>(StatusCode.SUCCESS, matches);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BaseResponse<Match> getMatchById(@PathVariable("id") final Long id) {
        logger.debug("/getMatchById request: {}", id);
        Match match = matchService.findById(id);
        return new BaseResponse<>(StatusCode.SUCCESS, match);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public BaseResponse<Match> updateMatch(@PathVariable("id") final Long id, @RequestBody final UpdateMatchRequest updateMatchDto, @RequestHeader("Authorization") final String accessToken) {
        logger.debug("/updateMatch request: {}", updateMatchDto.toString());
        final String token = accessToken.replace("Bearer ", "");
        Match match = matchService.updateMatch(token, id, updateMatchDto);
        return new BaseResponse<>(StatusCode.SUCCESS, match);
    }

    @DeleteMapping("/{matchId}")
    @ResponseBody
    public BaseResponse<Void> deleteMatch(@RequestHeader("Authorization") final String accessToken, @PathVariable("matchId") final Long matchId) {
        logger.debug("/deleteMatch request: {}", matchId);
        final String token = accessToken.replace("Bearer ", "");
        matchService.deleteById(token, matchId);
        return new BaseResponse<>(StatusCode.SUCCESS);
    }

    @PostMapping("/participation")
    @ResponseBody
    public BaseResponse<Participation> addParticipation(@RequestHeader("Authorization") final String accessToken, @RequestBody final CreateParticipationRequest createParticipationDto) {
        logger.debug("/addParticipation request: {}", createParticipationDto.toString());
        final String token = accessToken.replace("Bearer ", "");
        Participation participation = participationService.createParticipation(token, createParticipationDto);
        return new BaseResponse<>(StatusCode.SUCCESS, participation);
    }

    @GetMapping("/participation")
    @ResponseBody
    public BaseResponse<List<MatchParticipationResponse>> getParticipation(@RequestHeader("Authorization") final String accessToken) {
        logger.debug("/getParticipation request: {}", accessToken);
        final String token = accessToken.replace("Bearer ", "");
        List<MatchParticipationResponse> responses = matchService.findMatchParticipationByMemberId(token);
        return new BaseResponse<>(StatusCode.SUCCESS, responses);
    }
}