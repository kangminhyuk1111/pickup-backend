package core.pickupbackend.court.application.in;

import core.pickupbackend.court.domain.Court;
import core.pickupbackend.court.dto.request.GetCourtCommand;

import java.util.List;

public interface GetCourtsUseCase {
    List<Court> getAllCourts();
    Court getCourtById(GetCourtCommand command);
}
