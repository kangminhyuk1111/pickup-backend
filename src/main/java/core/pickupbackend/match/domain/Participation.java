package core.pickupbackend.match.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Participation {
    private static final List<String> VALID_STATUSES = List.of("PENDING", "ACCEPTED", "REJECTED");

    private Long id;
    private Long memberId;
    private Long matchId;
    private ParticipationStatus status; // enum
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Participation() {
    }

    public Participation(final Long memberId, final Long matchId, final ParticipationStatus status, final String message) {
        validateMemberId(memberId);
        validateMatchId(matchId);
        validateStatus(status);

        this.memberId = memberId;
        this.matchId = matchId;
        this.status = status;
        this.message = message;
    }

    public Participation(final Long id, final Long memberId, final Long matchId, final ParticipationStatus status, final String message, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        validateMemberId(memberId);
        validateMatchId(matchId);
        validateStatus(status);

        this.id = id;
        this.memberId = memberId;
        this.matchId = matchId;
        this.status = status;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private void validateMemberId(Long memberId) {
        if (memberId == null) {
            throw new IllegalArgumentException("사용자 ID는 필수입니다");
        }
    }

    private void validateMatchId(Long matchId) {
        if (matchId == null) {
            throw new IllegalArgumentException("매칭 ID는 필수입니다");
        }
    }

    private void validateStatus(ParticipationStatus status) {
        if (status == null || status.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("상태값은 필수입니다");
        }
        if (!VALID_STATUSES.contains(status.getStatus().toUpperCase())) {
            throw new IllegalArgumentException(
                    String.format("유효하지 않은 상태값입니다. 가능한 값: %s", String.join(", ", VALID_STATUSES))
            );
        }
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", matchId=" + matchId +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}