package core.pickupbackend.match.dto.response;

import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.domain.MatchStatus;
import core.pickupbackend.member.domain.type.Level;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record MatchResponse(
        Long id,
        String title,
        String description,
        String courtName,
        String location,
        LocalDate date,
        LocalTime time,
        Level level,
        Integer currentPlayers,
        Integer maxPlayers,
        Long cost,
        String rules,
        Long hostId,
        MatchStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static MatchResponse from(Match match) {
        return new MatchResponse(
                match.getId(),
                match.getTitle(),
                match.getDescription(),
                match.getCourtName(),
                match.getLocation(),
                match.getDate(),
                match.getTime(),
                match.getLevel(),
                match.getCurrentPlayers(),
                match.getMaxPlayers(),
                match.getCost(),
                match.getRules(),
                match.getHostId(),
                match.getStatus(),
                match.getCreatedAt(),
                match.getUpdatedAt()
        );
    }
}