package core.pickupbackend.match.service;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ApplicationMatchException;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.dto.request.CreateMatchRequest;
import core.pickupbackend.match.dto.request.UpdateMatchRequest;
import core.pickupbackend.match.dto.response.MatchParticipationResponse;
import core.pickupbackend.match.dto.response.ParticipationMemberResponse;
import core.pickupbackend.match.dto.response.ParticipationWithUserResponse;
import core.pickupbackend.match.repository.MatchRepository;
import core.pickupbackend.match.repository.ParticipationRepository;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchService.class);

    private final MatchRepository matchRepository;
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final ParticipationRepository participationRepository;

    public MatchService(final MatchRepository matchRepository, final MemberService memberService, final TokenProvider tokenProvider, final ParticipationRepository participationRepository) {
        this.matchRepository = matchRepository;
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
        this.participationRepository = participationRepository;
    }

    public Match createMatch(final String accessToken, final CreateMatchRequest createMatchDto) {
        final String email = tokenProvider.extractEmailFromToken(accessToken);
        final Member member = memberService.getMemberByEmail(email);
        return matchRepository.save(createMatchDto.toEntity(member.getId()));
    }

    public Match findById(final Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new ApplicationMatchException(ErrorCode.NOT_FOUND_MATCH));
    }

    public List<MatchParticipationResponse> findMatchParticipationByMemberId(final String token) {
        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);
        final Long memberId = member.getId();

        final List<Match> matches = matchRepository.findByMemberId(memberId);

        return matches.stream()
                .map(match -> {
                    final List<Participation> matchParticipations =
                            participationRepository.findParticipationsByMatchId(match.getId());

                    final List<ParticipationWithUserResponse> participationResponses =
                            matchParticipations.stream()
                                    .map(participation -> {
                                        final Member participationMember = memberService.getMemberById(participation.getUserId());
                                        final ParticipationMemberResponse memberDto = new ParticipationMemberResponse(participationMember);
                                        return new ParticipationWithUserResponse(memberDto, participation);
                                    })
                                    .toList();

                    return new MatchParticipationResponse(match, participationResponses);
                })
                .toList();
    }

    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    public void deleteById(final String token, final Long matchId) {
        matchRepository.findById(matchId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_MATCH));

        final String email = tokenProvider.extractEmailFromToken(token);
        memberService.getMemberByEmail(email);

        matchRepository.deleteById(matchId);
    }

    public Match updateMatch(final String token, final Long matchId, final UpdateMatchRequest updateMatchDto) {
        final Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_MATCH));

        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);

        final Match updateMatch = updateMatchDto.toEntity(member.getId());

        return matchRepository.update(match.getId(), updateMatch);
    }

    public void closeMatch(final Long matchId) {
        final Match findMatch = findById(matchId);
        findMatch.closeMatch();
    }
}
