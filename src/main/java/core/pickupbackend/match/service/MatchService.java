package core.pickupbackend.match.service;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ApplicationMatchException;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.dto.CreateMatchDto;
import core.pickupbackend.match.dto.UpdateMatchDto;
import core.pickupbackend.match.repository.MatchRepository;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    public MatchService(final MatchRepository matchRepository, final MemberService memberService, final TokenProvider tokenProvider) {
        this.matchRepository = matchRepository;
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
    }

    public Match createMatch(final String accessToken, final CreateMatchDto createMatchDto) {
        final String email = tokenProvider.extractEmailFromToken(accessToken);
        final Member member = memberService.getMemberByEmail(email);
        return matchRepository.save(createMatchDto.toEntity(member.getId()));
    }

    public Match findById(final Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new ApplicationMatchException(ErrorCode.NOT_FOUND_MATCH));
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public void deleteById(final Long id) {
        matchRepository.deleteById(id);
    }

    public Match updateMatch(final String token, final Long matchId, final UpdateMatchDto updateMatchDto) {
        // 매치 조회
        final Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_MATCH));

        // 호스트 확인
        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);

        // 매치 정보 업데이트
        final Match updateMatch = updateMatchDto.toEntity(member.getId());

        // 업데이트된 매치 저장
        return matchRepository.update(match.getId(), updateMatch);
    }
}
