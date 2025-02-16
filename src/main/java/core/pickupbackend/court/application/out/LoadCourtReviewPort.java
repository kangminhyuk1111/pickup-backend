package core.pickupbackend.court.application.out;

import core.pickupbackend.court.domain.CourtReview;

import java.util.List;

public interface LoadCourtReviewPort {
    List<CourtReview> loadCourtReviewById(Long courtId);
}
