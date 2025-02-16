package core.pickupbackend.court.application.in;

import core.pickupbackend.court.domain.CourtReview;
import core.pickupbackend.court.dto.request.GetCourtReviewCommand;

import java.util.List;

public interface GetCourtReviewUseCase {
    List<CourtReview> getReviewByCourtId(GetCourtReviewCommand command);
}
