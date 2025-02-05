package core.pickupbackend.court.repository;

import core.pickupbackend.court.domain.Court;

import java.util.List;
import java.util.Optional;

public interface CourtRepository {

    List<Court> getAllCourts();

    Optional<Court> getCourtById(Long courtId);
}
