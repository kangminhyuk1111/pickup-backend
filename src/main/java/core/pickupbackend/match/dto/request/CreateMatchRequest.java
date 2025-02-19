package core.pickupbackend.match.dto.request;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.MatchStatus;
import core.pickupbackend.member.domain.type.Level;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateMatchRequest(
        String title,
        String description,
        String courtName,
        String district,
        String locationDetail,
        LocalDate date,
        LocalTime time,
        Level level,
        Integer currentPlayers,
        Integer maxPlayers,
        Long hostId,
        Long cost,
        String rules,
        MatchStatus status
) {
    public CreateMatchRequest {
        if (status == null) {
            status = MatchStatus.OPEN;
        }
    }

    public CreateMatchRequest(String title, String description, String courtName, String district, String locationDetail, LocalDate date, LocalTime time, Level level, int currentPlayers, int maxPlayers, long cost, String rules) {
        this(title, description, courtName, district, locationDetail,
                date, time, level, currentPlayers, maxPlayers,
                null, cost, rules, MatchStatus.OPEN);
    }

    public CreateMatchRequest(String title, String description, String courtName, String district, String locationDetail, LocalDate date, LocalTime time, Level level, int currentPlayers, int maxPlayers, Long hostId, long cost, String rules) {
        this(title, description, courtName, district, locationDetail,
                date, time, level, currentPlayers, maxPlayers,
                hostId, cost, rules, MatchStatus.OPEN);
    }

    public Match toEntity() {
        return new Match(title, description, courtName, district, locationDetail, date, time, level, currentPlayers, maxPlayers, hostId, cost, rules);
    }

    public Match toEntity(Long hostId) {
        return new Match(title, description, courtName, district, locationDetail, date, time, level, currentPlayers, maxPlayers, hostId, cost, rules);
    }
}