package core.pickupbackend.match.application.in;

import core.pickupbackend.match.dto.response.MatchesPagingResponse;

public interface FindAllMatchesUseCase {
    MatchesPagingResponse findAll(Integer page, Integer size);
}
