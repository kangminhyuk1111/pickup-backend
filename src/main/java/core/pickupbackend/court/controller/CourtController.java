package core.pickupbackend.court.controller;

import core.pickupbackend.court.domain.Court;
import core.pickupbackend.court.domain.CourtReview;
import core.pickupbackend.court.service.CourtService;
import core.pickupbackend.global.common.code.StatusCode;
import core.pickupbackend.global.common.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "코트 API")
@RestController
@RequestMapping("/courts")
public class CourtController {

    private final static Logger logger = LoggerFactory.getLogger(CourtController.class);

    private final CourtService courtService;

    public CourtController(final CourtService courtService) {
        this.courtService = courtService;
    }

    @Operation(summary = "전체 코트 정보 조회", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping
    @ResponseBody
    public BaseResponse<List<Court>> getAllCourts() {
        logger.debug("/courts request");
        return new BaseResponse<>(StatusCode.SUCCESS, courtService.getAllCourts());
    }

    @Operation(summary = "코트 정보 단건 조회", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping("/{courtId}")
    @ResponseBody
    public BaseResponse<Court> getCourtReviewByCourtId(@PathVariable final Long courtId) {
        logger.debug("/courts/:id request: {}", courtId);
        return new BaseResponse<>(StatusCode.SUCCESS, courtService.getCourtById(courtId));
    }
}
