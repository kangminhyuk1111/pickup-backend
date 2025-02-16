package core.pickupbackend.court.domain.mapper;

import core.pickupbackend.court.domain.Court;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CourtRowMapper implements RowMapper<Court> {

    @Override
    public Court mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String location = rs.getString("location");
        String address = rs.getString("address");
        Double latitude = rs.getDouble("latitude");
        Double longitude = rs.getDouble("longitude");
        Integer hoops = rs.getInt("hoops");
        String surface = rs.getString("surface");
        Boolean lighting = rs.getBoolean("lighting");
        Boolean parking = rs.getBoolean("parking");
        Double rating = rs.getDouble("rating");
        String bestTime = rs.getString("best_time");

        List<String> images = null;
        String imagesString = rs.getString("images");
        if (imagesString != null) {
            images = Arrays.asList(imagesString.split(","));
        }

        List<String> facilities = null;
        String facilitiesString = rs.getString("facilities");
        if (facilitiesString != null) {
            facilities = Arrays.asList(facilitiesString.split(","));
        }

        return new Court(id, name, location, address, latitude, longitude, hoops,
                surface, lighting, parking, rating, images, facilities, bestTime);
    }
}
