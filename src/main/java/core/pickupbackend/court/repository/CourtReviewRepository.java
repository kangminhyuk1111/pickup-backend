package core.pickupbackend.court.repository;

import core.pickupbackend.court.domain.CourtReview;

import java.util.List;

public interface CourtReviewRepository {
    List<CourtReview> getReviewById(Long courtId);
}
