package core.pickupbackend.match.dto.request;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.member.domain.type.Level;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateMatchRequest {
    private String title;
    private String description;
    private String courtName;
    private String location;
    private LocalDate date;
    private LocalTime time;
    private Level level;
    private Integer currentPlayers;
    private Integer maxPlayers;
    private Long cost;
    private String rules;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCourtName() {
        return courtName;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Level getLevel() {
        return level;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public Long getCost() {
        return cost;
    }

    public String getRules() {
        return rules;
    }

    public Match toEntity(Long hostId) {
        return new Match(title, description, courtName, location, date, time, level, currentPlayers, maxPlayers, hostId, cost, rules);
    }
}