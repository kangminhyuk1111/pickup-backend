package core.pickupbackend.match.domain;

import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ApplicationMatchException;
import core.pickupbackend.member.domain.type.Level;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Match {
    private Long id;
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
    private Long hostId;
    private MatchStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Match() {
    }

    public Match(final String title, final String description, final String courtName, final String location, final LocalDate date, final LocalTime time, final Level level, final int currentPlayers, final int maxPlayers, final Long hostId,final long cost, final String rules) {
        validateTitle(title);
        validateDescription(description);
        validateCourtName(courtName);
        validateLocation(location);
        validateDateAndTime(date, time);
        validatePlayers(currentPlayers, maxPlayers);
        validateCost(cost);

        this.title = title;
        this.description = description;
        this.courtName = courtName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.level = level;
        this.currentPlayers = currentPlayers;
        this.maxPlayers = maxPlayers;
        this.hostId = hostId;
        this.cost = cost;
        this.rules = rules;

        this.status = MatchStatus.OPEN;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new ApplicationMatchException(ErrorCode.TITLE_BLANK_EXCEPTION);
        }
        if (title.length() > 100) {
            throw new ApplicationMatchException(ErrorCode.TITLE_LENGTH_EXCEPTION);
        }
    }

    private void validateDescription(String description) {
        if (description != null && description.length() > 2000) {
            throw new ApplicationMatchException(ErrorCode.DESCRIPTION_BLANK_EXCEPTION);
        }
    }

    private void validateCourtName(String courtName) {
        if (courtName == null || courtName.isBlank()) {
            throw new ApplicationMatchException(ErrorCode.COURT_BLANK_EXCEPTION);
        }
    }

    private void validateLocation(String location) {
        if (location == null || location.isBlank()) {
            throw new ApplicationMatchException(ErrorCode.LOCATION_BLANK_EXCEPTION);
        }
    }

    private void validateDateAndTime(LocalDate date, LocalTime time) {
        if (date == null || time == null) {
            throw new ApplicationMatchException(ErrorCode.DATE_BLANK_EXCEPTION);
        }
        if (date.isBefore(LocalDate.now())) {
            throw new ApplicationMatchException(ErrorCode.DATE_PAST_EXCEPTION);
        }
    }

    private void validatePlayers(int currentPlayers, int maxPlayers) {
        if (currentPlayers < 0) {
            throw new ApplicationMatchException(ErrorCode.PLAYERS_NEGATIVE_EXCEPTION);
        }
        if (maxPlayers <= 0) {
            throw new ApplicationMatchException(ErrorCode.PLAYERS_NEGATIVE_EXCEPTION);
        }
        if (currentPlayers > maxPlayers) {
            throw new ApplicationMatchException(ErrorCode.PLAYERS_NEGATIVE_EXCEPTION);
        }
    }

    private void validateCost(long cost) {
        if (cost < 0) {
            throw new ApplicationMatchException(ErrorCode.COAST_NEGATIVE_EXCEPTION);
        }
    }

    public Long getId() {
        return id;
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

    public Long getHostId() {
        return hostId;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setCourtName(final String courtName) {
        this.courtName = courtName;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public void setTime(final LocalTime time) {
        this.time = time;
    }

    public void setLevel(final Level level) {
        this.level = level;
    }

    public void setCurrentPlayers(final int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public void setMaxPlayers(final int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setCost(final long cost) {
        this.cost = cost;
    }

    public void setRules(final String rules) {
        this.rules = rules;
    }

    public void setHostId(final Long hostId) {
        this.hostId = hostId;
    }

    public void setStatus(final MatchStatus status) {
        this.status = status;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", courtName='" + courtName + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", level=" + level +
                ", currentPlayers=" + currentPlayers +
                ", maxPlayers=" + maxPlayers +
                ", cost=" + cost +
                ", rules='" + rules + '\'' +
                ", hostId=" + hostId +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
