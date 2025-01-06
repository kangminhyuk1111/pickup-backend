package core.pickupbackend.match.service;

import core.pickupbackend.match.domain.Participation;
import core.pickupbackend.match.dto.request.CreateParticipationRequest;
import core.pickupbackend.match.repository.ParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {

    private final ParticipationRepository participationRepository;

    public ParticipationService(final ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public Participation createParticipation(final CreateParticipationRequest participationDto) {
        return this.participationRepository.createParticipation(participationDto.toEntity());
    }

    public List<Participation> findByMatchId(final Long matchId) {
        return this.participationRepository.findParticipationsByMatchId(matchId);
    }
}
