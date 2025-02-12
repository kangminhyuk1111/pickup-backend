package core.pickupbackend.court.dto.response;

import core.pickupbackend.court.domain.Court;

import java.util.List;

public record CourtResponse(
        Long id,
        String name,
        String location,
        String address,
        Double latitude,
        Double longitude,
        Integer hoops,
        String surface,
        Boolean lighting,
        Boolean parking,
        Double rating,
        List<String> images,
        List<String> facilities,
        String bestTime
) {
    public static CourtResponse from(Court court) {
        return new CourtResponse(
                court.getId(),
                court.getName(),
                court.getLocation(),
                court.getAddress(),
                court.getLatitude(),
                court.getLongitude(),
                court.getHoops(),
                court.getSurface(),
                court.getLighting(),
                court.getParking(),
                court.getRating(),
                court.getImages(),
                court.getFacilities(),
                court.getBestTime()
        );
    }

    public static List<CourtResponse> from(List<Court> courts) {
        return courts.stream()
                .map(CourtResponse::from).toList();
    }
}