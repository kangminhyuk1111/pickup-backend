package core.pickupbackend.court.domain;

import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;

import java.util.List;

public class Court {
    private static final double MIN_LATITUDE = -90.0;
    private static final double MAX_LATITUDE = 90.0;
    private static final double MIN_LONGITUDE = -180.0;
    private static final double MAX_LONGITUDE = 180.0;
    private static final double MIN_RATING = 0.0;
    private static final double MAX_RATING = 5.0;

    private Long id;
    private String name;
    private String location;
    private String address;
    private Double latitude; // x 좌표
    private Double longitude; // y 좌표
    private Integer hoops; // 골대 개수
    private String surface; // 바닥 재질
    private Boolean lighting; // 불 켜주는지 여부
    private Boolean parking; // 주차가능 여부
    private Double rating; // 평점
    private List<String> images;
    private List<String> facilities; // 부대 시설
    private String bestTime; // 추천 시간대

    public Court(final Long id, final String name, final String location, final String address, final Double latitude, final Double longitude, final Integer hoops, final String surface, final Boolean lighting, final Boolean parking, final Double rating, final List<String> images, final List<String> facilities, final String bestTime) {
        validateCourtField(name, location, address, latitude, longitude, hoops, surface, lighting, parking, rating);

        this.id = id;
        this.name = name;
        this.location = location;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hoops = hoops;
        this.surface = surface;
        this.lighting = lighting;
        this.parking = parking;
        this.rating = rating;
        this.images = images;
        this.facilities = facilities;
        this.bestTime = bestTime;
    }

    private void validateCourtField(final String name, final String location, final String address, final Double latitude, final Double longitude, final Integer hoops, final String surface, final Boolean lighting, final Boolean parking, final Double rating) {
        validateName(name);
        validateLocation(location);
        validateAddress(address);
        validateLatitude(latitude);
        validateLongitude(longitude);
        validateHoops(hoops);
        validateSurface(surface);
        validateLighting(lighting);
        validateParking(parking);
        validateRating(rating);
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new ApplicationException(ErrorCode.COURT_NAME_NOT_NULL);
        }
    }

    private void validateLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new ApplicationException(ErrorCode.COURT_LOCATION_NOT_NULL);
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new ApplicationException(ErrorCode.COURT_ADDRESS_NOT_NULL);
        }
    }

    private void validateLatitude(Double latitude) {
        if (latitude == null) {
            throw new ApplicationException(ErrorCode.COURT_LATITUDE_NOT_NULL);
        }
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new ApplicationException(ErrorCode.COURT_LATITUDE_RANGE);
        }
    }

    private void validateLongitude(Double longitude) {
        if (longitude == null) {
            throw new ApplicationException(ErrorCode.COURT_LONGITUDE_NOT_NULL);
        }
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new ApplicationException(ErrorCode.COURT_LONGITUDE_RANGE);
        }
    }

    private void validateHoops(Integer hoops) {
        if (hoops == null) {
            throw new ApplicationException(ErrorCode.COURT_HOOPS_NOT_NULL);
        }
        if (hoops <= 0) {
            throw new ApplicationException(ErrorCode.COURT_HOOPS_RANGE);
        }
    }

    private void validateSurface(String surface) {
        if (surface == null || surface.trim().isEmpty()) {
            throw new ApplicationException(ErrorCode.COURT_SURFACE_NOT_NULL);
        }
    }

    private void validateLighting(Boolean lighting) {
        if (lighting == null) {
            throw new ApplicationException(ErrorCode.COURT_LIGHTING_NOT_NULL);
        }
    }

    private void validateParking(Boolean parking) {
        if (parking == null) {
            throw new ApplicationException(ErrorCode.COURT_PARKING_NOT_NULL);
        }
    }

    private void validateRating(Double rating) {
        if (rating == null) {
            throw new ApplicationException(ErrorCode.COURT_RATING_NOT_NULL);
        }
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new ApplicationException(ErrorCode.COURT_RATING_RANGE);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAddress() {
        return address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getHoops() {
        return hoops;
    }

    public String getSurface() {
        return surface;
    }

    public Boolean getLighting() {
        return lighting;
    }

    public Boolean getParking() {
        return parking;
    }

    public Double getRating() {
        return rating;
    }

    public List<String> getImages() {
        return images;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public String getBestTime() {
        return bestTime;
    }
}
