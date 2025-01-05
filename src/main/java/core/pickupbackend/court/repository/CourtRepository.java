package core.pickupbackend.court.repository;

import core.pickupbackend.court.domain.Court;

import java.util.List;

public interface CourtRepository {

    List<Court> getAllCourts();
}
