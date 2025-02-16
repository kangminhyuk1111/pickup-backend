package core.pickupbackend.court.application.service;

import core.pickupbackend.court.application.in.GetCourtReviewUseCase;
import core.pickupbackend.court.application.in.GetCourtsUseCase;
import core.pickupbackend.court.domain.Court;
import core.pickupbackend.court.domain.CourtReview;
import core.pickupbackend.court.application.out.LoadCourtPort;
import core.pickupbackend.court.application.out.LoadCourtReviewPort;
import core.pickupbackend.court.dto.request.GetCourtCommand;
import core.pickupbackend.court.dto.request.GetCourtReviewCommand;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultCourtService implements GetCourtsUseCase, GetCourtReviewUseCase {

    private final LoadCourtPort loadCourtPort;
    private final LoadCourtReviewPort loadCourtReviewPort;

    public DefaultCourtService(final LoadCourtPort loadCourtPort, final LoadCourtReviewPort loadCourtReviewPort) {
        this.loadCourtPort = loadCourtPort;
        this.loadCourtReviewPort = loadCourtReviewPort;
    }

    @Override
    public List<Court> getAllCourts() {
        return loadCourtPort.loadAllCourts();
    }

    @Override
    public Court getCourtById(GetCourtCommand command) {
        return loadCourtPort.loadCourtById(command.id()).orElseThrow(() -> new ApplicationException(ErrorCode.COURT_NOT_FOUND));
    }

    @Override
    public List<CourtReview> getReviewByCourtId(GetCourtReviewCommand command) {
        return loadCourtReviewPort.loadCourtReviewById(command.id());
    }
}
