package core.pickupbackend.court.controller;

import core.pickupbackend.court.domain.Court;
import core.pickupbackend.court.domain.CourtReview;
import core.pickupbackend.court.service.CourtService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {

    private final CourtService courtService;

    public CourtController(final CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    @ResponseBody
    public List<Court> getAllCourts() {
        return courtService.getAllCourts();
    }

    @GetMapping("/{courtId}")
    @ResponseBody
    public List<CourtReview> getCourtReviewByCourtId(@PathVariable final Long courtId) {
        return courtService.getReviewById(courtId);
    }
}
