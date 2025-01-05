package core.pickupbackend.court.domain;

import java.util.List;

public class Court {
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
