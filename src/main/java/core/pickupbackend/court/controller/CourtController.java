package core.pickupbackend.court.controller;

import core.pickupbackend.court.domain.Court;
import core.pickupbackend.court.domain.CourtReview;
import core.pickupbackend.court.service.CourtService;
import core.pickupbackend.global.common.code.StatusCode;
import core.pickupbackend.global.common.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courts")
public class CourtController {

    private final static Logger logger = LoggerFactory.getLogger(CourtController.class);

    private final CourtService courtService;

    public CourtController(final CourtService courtService) {
        this.courtService = courtService;
    }

    @GetMapping
    @ResponseBody
    public BaseResponse<List<Court>> getAllCourts() {
        logger.debug("/courts request");
        return new BaseResponse<>(StatusCode.SUCCESS, courtService.getAllCourts());
    }

    @GetMapping("/{courtId}")
    @ResponseBody
    public BaseResponse<List<CourtReview>> getCourtReviewByCourtId(@PathVariable final Long courtId) {
        logger.debug("/courts/:id request: {}", courtId);
        return new BaseResponse<>(StatusCode.SUCCESS, courtService.getReviewById(courtId));
    }
}
