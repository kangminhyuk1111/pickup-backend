package core.pickupbackend.match.controller;

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
    public Match createMatch(@RequestBody final CreateMatchRequest createMatchDto, @RequestHeader("Authorization") final String accessToken) {
        final String token = accessToken.replace("Bearer ", "");
        return matchService.createMatch(token, createMatchDto);
    }

    @GetMapping
    @ResponseBody
    public List<Match> getMatches() {
        return matchService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Match getMatchById(@PathVariable("id") final Long id) {
        return matchService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Match updateMatch(@PathVariable("id") final Long id, @RequestBody final UpdateMatchRequest updateMatchDto, @RequestHeader("Authorization") final String accessToken) {
        final String token = accessToken.replace("Bearer ", "");
        return matchService.updateMatch(token, id, updateMatchDto);
    }

    @DeleteMapping("/{matchId}")
    @ResponseBody
    public void deleteMatch(@RequestHeader("Authorization") final String accessToken, @PathVariable("matchId") final Long matchId) {
        final String token = accessToken.replace("Bearer ", "");
        matchService.deleteById(token ,matchId);
    }

    @PostMapping("/participation")
    @ResponseBody
    public Participation addParticipation(@RequestBody final CreateParticipationRequest createParticipationDto) {
        return participationService.createParticipation(createParticipationDto);
    }

    @GetMapping("/participation")
    @ResponseBody
    public List<MatchParticipationResponse> getParticipation(@RequestHeader("Authorization") final String accessToken) {
        final String token = accessToken.replace("Bearer ", "");
        return matchService.findMatchParticipationByMemberId(token);
    }
}
