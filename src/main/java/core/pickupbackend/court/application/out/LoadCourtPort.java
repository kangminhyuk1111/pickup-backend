package core.pickupbackend.court.application.out;

import core.pickupbackend.court.domain.Court;

import java.util.List;
import java.util.Optional;

public interface LoadCourtPort {
    List<Court> loadAllCourts();
    Optional<Court> loadCourtById(Long courtId);
}
