package core.pickupbackend.match.application.service;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.match.application.in.MatchService;
import core.pickupbackend.match.application.in.ParticipationService;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.dto.request.CreateParticipationRequest;
import core.pickupbackend.match.application.out.ParticipationRepository;
import core.pickupbackend.member.application.in.MemberService;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.application.service.DefaultMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultParticipationService implements ParticipationService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultParticipationService.class);

    private final ParticipationRepository participationRepository;
    private final MatchService matchService;
    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    public DefaultParticipationService(final ParticipationRepository participationRepository, final MatchService matchService, final TokenProvider tokenProvider, final DefaultMemberService memberService) {
        this.participationRepository = participationRepository;
        this.matchService = matchService;
        this.tokenProvider = tokenProvider;
        this.memberService = memberService;
    }

    @Override
    public Participation createParticipation(final String token, final CreateParticipationRequest createParticipationRequest) {
        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);

        Match match = matchService.findById(createParticipationRequest.matchingId());

        if (match.getHostId().equals(member.getId())) {
            throw new ApplicationException(ErrorCode.CREATOR_CAN_NOT_CREATE_PARTICIPATION);
        }

        if (match.getCurrentPlayers() >= match.getMaxPlayers()) {
            throw new ApplicationException(ErrorCode.MATCH_IS_FULL);
        }

        return this.participationRepository.createParticipation(createParticipationRequest.toEntity(member.getId()));
    }
}
