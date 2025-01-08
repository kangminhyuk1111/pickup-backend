package core.pickupbackend.court.domain;

import java.time.LocalDateTime;

public class CourtReview {
    private static final int MAX_CONTENT_LENGTH = 1000;
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 5;

    private Long id;
    private Long courtId;
    private Long userId;
    private Integer rating;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;

    public CourtReview(final Long id, final Long courtId, final Long userId, final Integer rating,
                       final String content, final LocalDateTime createdAt, final LocalDateTime updatedAt,
                       final boolean isDeleted) {
        this.id = id;
        this.courtId = courtId;
        this.userId = userId;
        this.rating = rating;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public void validate() {
        validateId();
        validateCourtId();
        validateUserId();
        validateRating();
        validateContent();
        validateCreatedAt();
        validateUpdatedAt();
        validateTimeOrder();
    }

    private void validateId() {
        if (id == null) {
            throw new IllegalArgumentException("리뷰 ID는 null일 수 없습니다");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("리뷰 ID는 양수여야 합니다");
        }
    }

    private void validateCourtId() {
        if (courtId == null) {
            throw new IllegalArgumentException("농구장 ID는 null일 수 없습니다");
        }
        if (courtId <= 0) {
            throw new IllegalArgumentException("농구장 ID는 양수여야 합니다");
        }
    }

    private void validateUserId() {
        if (userId == null) {
            throw new IllegalArgumentException("사용자 ID는 null일 수 없습니다");
        }
        if (userId <= 0) {
            throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
        }
    }

    private void validateRating() {
        if (rating == null) {
            throw new IllegalArgumentException("평점은 null일 수 없습니다");
        }
        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new IllegalArgumentException(String.format("평점은 %d에서 %d 사이여야 합니다", MIN_RATING, MAX_RATING));
        }
    }

    private void validateContent() {
        if (content == null) {
            throw new IllegalArgumentException("리뷰 내용은 null일 수 없습니다");
        }
        if (content.trim().isEmpty()) {
            throw new IllegalArgumentException("리뷰 내용은 비어있을 수 없습니다");
        }
        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new IllegalArgumentException(String.format("리뷰 내용은 %d자를 초과할 수 없습니다", MAX_CONTENT_LENGTH));
        }
    }

    private void validateCreatedAt() {
        if (createdAt == null) {
            throw new IllegalArgumentException("생성 시간은 null일 수 없습니다");
        }
    }

    private void validateUpdatedAt() {
        if (updatedAt == null) {
            throw new IllegalArgumentException("수정 시간은 null일 수 없습니다");
        }
    }

    private void validateTimeOrder() {
        if (createdAt != null && updatedAt != null && updatedAt.isBefore(createdAt)) {
            throw new IllegalArgumentException("수정 시간이 생성 시간보다 빠를 수 없습니다");
        }
    }

    // Getters
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