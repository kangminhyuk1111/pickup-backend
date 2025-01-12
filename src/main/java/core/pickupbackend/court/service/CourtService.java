package core.pickupbackend.court.service;

import core.pickupbackend.court.domain.Court;
import core.pickupbackend.court.domain.CourtReview;
import core.pickupbackend.court.repository.CourtRepository;
import core.pickupbackend.court.repository.CourtReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtService {

    private final CourtRepository courtRepository;
    private final CourtReviewRepository courtReviewRepository;

    public CourtService(final CourtRepository courtRepository, final CourtReviewRepository courtReviewRepository) {
        this.courtRepository = courtRepository;
        this.courtReviewRepository = courtReviewRepository;
    }

    public List<Court> getAllCourts() {
        return courtRepository.getAllCourts();
    }

    public List<CourtReview> getReviewById(final Long courtId) {
        return courtReviewRepository.getReviewById(courtId);
    }
}
