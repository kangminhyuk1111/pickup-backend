package core.pickupbackend.match.dto;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.member.domain.type.Level;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateMatchDto {
    private String title;
    private String description;
    private String courtName;
    private String location;
    private LocalDate date;
    private LocalTime time;
    private Level level;
    private int currentPlayers;
    private int maxPlayers;
    private long cost;
    private String rules;

    public CreateMatchDto(final String title, final String description, final String courtName, final String location, final LocalDate date, final LocalTime time, final Level level, final int currentPlayers, final int maxPlayers, final long cost, final String rules) {
        this.title = title;
        this.description = description;
        this.courtName = courtName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.level = level;
        this.currentPlayers = currentPlayers;
        this.maxPlayers = maxPlayers;
        this.cost = cost;
        this.rules = rules;
    }

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

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public long getCost() {
        return cost;
    }

    public String getRules() {
        return rules;
    }

    public Match toEntity() {
        return new Match(title, description, courtName, location, date, time, level, currentPlayers, maxPlayers, cost, rules);
    }
}
