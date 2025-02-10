package core.pickupbackend.match.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.member.domain.type.Level;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateMatchRequest(
        @Schema(example = "농구 한판 하실 분")
        String title,

        @Schema(example = "서초에서 농구하실 분 구합니다")
        String description,

        @Schema(example = "서초 체육관")
        String courtName,

        @Schema(example = "서울시 서초구")
        String location,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @Schema(example = "2025-02-10")
        LocalDate date,

        @JsonFormat(pattern = "HH:mm:ss")
        @Schema(example = "14:30:00")
        LocalTime time,

        @Schema(example = "BEGINNER")
        Level level,

        @Schema(example = "1")
        Integer currentPlayers,

        @Schema(example = "4")
        Integer maxPlayers,

        @Schema(example = "5000")
        Long cost,

        @Schema(example = "실력 무관, 매너 있는 분")
        String rules
) {
    public Match toEntity(Long hostId) {
        return new Match(
                title,
                description,
                courtName,
                location,
                date,
                time,
                level,
                currentPlayers,
                maxPlayers,
                hostId,
                cost,
                rules
        );
    }
}