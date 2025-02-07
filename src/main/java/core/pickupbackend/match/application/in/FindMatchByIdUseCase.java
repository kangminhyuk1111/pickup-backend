package core.pickupbackend.match.application.in;

import core.pickupbackend.match.domain.Match;

public interface FindMatchByIdUseCase {
    Match findById(Long id);
}
