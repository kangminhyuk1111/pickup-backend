package core.pickupbackend.match.application.service;

import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.dto.request.CreateParticipationRequest;
import core.pickupbackend.match.application.out.ParticipationRepository;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultParticipationService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultParticipationService.class);

    private final ParticipationRepository participationRepository;
    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    public DefaultParticipationService(final ParticipationRepository participationRepository, final TokenProvider tokenProvider, final MemberService memberService) {
        this.participationRepository = participationRepository;
        this.tokenProvider = tokenProvider;
        this.memberService = memberService;
    }

    public Participation createParticipation(final String token, final CreateParticipationRequest createParticipationRequest) {
        final String email = tokenProvider.extractEmailFromToken(token);
        final Member member = memberService.getMemberByEmail(email);
        return this.participationRepository.createParticipation(createParticipationRequest.toEntity(member.getId()));
    }

    public List<Participation> findByMatchId(final Long matchId) {
        return this.participationRepository.findParticipationsByMatchId(matchId);
    }
}
