package core.pickupbackend.match.application.in;

import core.pickupbackend.match.domain.Match;

import java.util.List;

public interface FindAllMatchesUseCase {
    List<Match> findAll();
}
