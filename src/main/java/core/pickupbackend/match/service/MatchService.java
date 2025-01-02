package core.pickupbackend.match.service;

import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.MatchException;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.dto.CreateMatchDto;
import core.pickupbackend.match.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    public MatchService(final MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match createMatch(final CreateMatchDto createMatchDto) {
        return matchRepository.save(createMatchDto.toEntity());
    }

    public Match findById(final Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new MatchException(ErrorCode.NOT_FOUND_MATCH));
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }
}
