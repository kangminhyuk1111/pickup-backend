package core.pickupbackend.court.infra.controller.web;

import core.pickupbackend.court.domain.Court;
import core.pickupbackend.court.dto.request.GetCourtCommand;
import core.pickupbackend.court.dto.response.CourtResponse;
import core.pickupbackend.court.application.service.DefaultCourtService;
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

    private final DefaultCourtService courtService;

    public CourtController(final DefaultCourtService courtService) {
        this.courtService = courtService;
    }

    @Operation(summary = "전체 코트 정보 조회", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping
    @ResponseBody
    public List<CourtResponse> getAllCourts() {
        logger.debug("/courts request");
        final List<Court> courts = courtService.getAllCourts();
        return CourtResponse.from(courts);
    }

    @Operation(summary = "코트 정보 단건 조회", security = { @SecurityRequirement(name = "bearerAuth") })
    @GetMapping("/{courtId}")
    @ResponseBody
    public CourtResponse getCourtReviewByCourtId(@PathVariable final GetCourtCommand command) {
        logger.debug("/courts/:id request: {}", command);
        final Court court = courtService.getCourtById(command);
        return CourtResponse.from(court);
    }
}
