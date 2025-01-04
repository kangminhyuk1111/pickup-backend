package core.pickupbackend.match.controller;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.dto.CreateMatchDto;
import core.pickupbackend.match.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final MatchService matchService;

    public MatchController(final MatchService matchService) {
        logger.debug("create match controller");
        this.matchService = matchService;
    }

    @PostMapping
    @ResponseBody
    public Match createMatch(@RequestBody final CreateMatchDto createMatchDto, @RequestHeader("Authorization") final String accessToken) {
        logger.info(createMatchDto.toString());
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
}
