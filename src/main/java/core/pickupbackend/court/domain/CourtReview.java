package core.pickupbackend.court.domain;

import java.time.LocalDateTime;

public class CourtReview {
    private Long id;
    private Long courtId;
    private Long userId;
    private Integer rating;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

    public CourtReview(final Long id, final Long courtId, final Long userId, final Integer rating, final String content, final LocalDateTime createdAt, final LocalDateTime updatedAt, final boolean isDeleted) {
        this.id = id;
        this.courtId = courtId;
        this.userId = userId;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public Long getCourtId() {
        return courtId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }
}
