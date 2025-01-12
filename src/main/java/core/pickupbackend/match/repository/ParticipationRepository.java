package core.pickupbackend.match.repository;

import core.pickupbackend.match.domain.Participation;

import java.util.List;

public interface ParticipationRepository {

    Participation createParticipation(Participation participation);

    Participation findParticipationById(Long id);

    List<Participation> findParticipationsByMatchId(Long id);

    Participation updateParticipation(Participation participation);

    void deleteParticipation(long id);

    List<Participation> findAllParticipation();
}
