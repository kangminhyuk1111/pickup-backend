package core.pickupbackend.match.application.service;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ApplicationMatchException;
import core.pickupbackend.match.application.in.MatchService;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.dto.request.CreateMatchRequest;
import core.pickupbackend.match.dto.request.UpdateMatchRequest;
import core.pickupbackend.match.dto.request.UpdateParticipationRequest;
import core.pickupbackend.match.dto.response.MatchParticipationResponse;
import core.pickupbackend.match.dto.response.ParticipationMemberResponse;
import core.pickupbackend.match.dto.response.ParticipationWithUserResponse;
import core.pickupbackend.match.application.out.MatchRepository;
import core.pickupbackend.match.application.out.ParticipationRepository;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.application.service.DefaultMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultMatchService implements MatchService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultMatchService.class);

    private final MatchRepository matchRepository;
    private final DefaultMemberService memberService;
    private final TokenProvider tokenProvider;
    private final ParticipationRepository participationRepository;

    public DefaultMatchService(final MatchRepository matchRepository, final DefaultMemberService memberService, final TokenProvider tokenProvider, final ParticipationRepository participationRepository) {
        this.matchRepository = matchRepository;
        this.memberService = memberService;
        this.tokenProvider = tokenProvider;
        this.participationRepository = participationRepository;
    }

    @Transactional
    @Override
    public Match create(final String accessToken, final CreateMatchRequest createMatchDto) {
        final String email = tokenProvider.extractEmailFromToken(accessToken);
        final Member member = memberService.getMemberByEmail(email);
        return matchRepository.save(createMatchDto.toEntity(member.getId()));
    }

    public Match findById(final Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new ApplicationMatchException(ErrorCode.NOT_FOUND_MATCH));
    }

    @Override
    public List<MatchParticipationResponse> findMatchParticipationByMemberId(final String token) {
        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);
        final Long memberId = member.getId();

        final List<Match> matches = matchRepository.findByMemberId(memberId);

        return matches.stream()
                .map(match -> {
                    final List<Participation> matchParticipations = getParticipations(match);

                    final List<ParticipationWithUserResponse> participationResponses = getParticipationWithUserResponses(matchParticipations);

                    return new MatchParticipationResponse(match, participationResponses);
                }).toList();
    }

    @Override
    public List<Match> findAll() {
        return matchRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(final String token, final Long matchId) {
        matchRepository.findById(matchId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_MATCH));

        final String email = tokenProvider.extractEmailFromToken(token);
        memberService.getMemberByEmail(email);

        matchRepository.deleteById(matchId);
    }

    @Transactional
    @Override
    public Match update(final String token, final Long matchId, final UpdateMatchRequest updateMatchDto) {
        final Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_MATCH));

        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);

        final Match updateMatch = updateMatchDto.toEntity(member.getId());

        return matchRepository.update(match.getId(), updateMatch);
    }

    @Override
    public Participation matchAccept(final UpdateParticipationRequest updateParticipationRequest) {
        final Participation participation = participationRepository.findParticipationById(updateParticipationRequest.participationId());

        final Participation accept = participation.accept();

        participationRepository.updateParticipation(accept);

        return accept;
    }

    @Override
    public Participation matchRejected(final UpdateParticipationRequest updateParticipationRequest) {
        final Participation participation = participationRepository.findParticipationById(updateParticipationRequest.participationId());

        final Participation rejected = participation.rejected();

        participationRepository.updateParticipation(rejected);

        return rejected;
    }

    private List<Participation> getParticipations(final Match match) {
        return participationRepository.findParticipationsByMatchId(match.getId());
    }

    private List<ParticipationWithUserResponse> getParticipationWithUserResponses(final List<Participation> matchParticipations) {
        return matchParticipations.stream()
                .map(participation -> {
                    final Member participationMember = memberService.getMemberById(participation.getUserId());
                    final ParticipationMemberResponse memberDto = new ParticipationMemberResponse(participationMember);
                    return new ParticipationWithUserResponse(memberDto, participation);
                }).toList();
    }

}
