package core.pickupbackend.court.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatNoException;

class CourtReviewTest {
    private static final Long VALID_ID = 1L;
    private static final Long VALID_COURT_ID = 1L;
    private static final Long VALID_USER_ID = 1L;
    private static final Integer VALID_RATING = 5;
    private static final String VALID_CONTENT = "좋은 농구장입니다";
    private static final LocalDateTime VALID_CREATED_AT = LocalDateTime.of(2024, 1, 1, 9, 0);
    private static final LocalDateTime VALID_UPDATED_AT = LocalDateTime.of(2024, 1, 1, 10, 0);
    private static final boolean VALID_IS_DELETED = false;

    @Test
    void 모든_필드가_유효하면_검증을_통과한다() {
        CourtReview review = new CourtReview(
                VALID_ID,
                VALID_COURT_ID,
                VALID_USER_ID,
                VALID_RATING,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatNoException().isThrownBy(review::validate);
    }

    @Test
    void ID가_null이면_예외가_발생한다() {
        CourtReview review = new CourtReview(
                null,
                VALID_COURT_ID,
                VALID_USER_ID,
                VALID_RATING,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, -100})
    void ID가_양수가_아니면_예외가_발생한다(long invalidId) {
        CourtReview review = new CourtReview(
                invalidId,
                VALID_COURT_ID,
                VALID_USER_ID,
                VALID_RATING,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 농구장ID가_null이면_예외가_발생한다() {
        CourtReview review = new CourtReview(
                VALID_ID,
                null,
                VALID_USER_ID,
                VALID_RATING,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, -100})
    void 농구장ID가_양수가_아니면_예외가_발생한다(long invalidCourtId) {
        CourtReview review = new CourtReview(
                VALID_ID,
                invalidCourtId,
                VALID_USER_ID,
                VALID_RATING,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 사용자ID가_null이면_예외가_발생한다() {
        CourtReview review = new CourtReview(
                VALID_ID,
                VALID_COURT_ID,
                null,
                VALID_RATING,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {0, -1, -100})
    void 사용자ID가_양수가_아니면_예외가_발생한다(long invalidUserId) {
        CourtReview review = new CourtReview(
                VALID_ID,
                VALID_COURT_ID,
                invalidUserId,
                VALID_RATING,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 평점이_null이면_예외가_발생한다() {
        CourtReview review = new CourtReview(
                VALID_ID,
                VALID_COURT_ID,
                VALID_USER_ID,
                null,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -5, 6, 10})
    void 평점이_1에서_5_범위를_벗어나면_예외가_발생한다(int invalidRating) {
        CourtReview review = new CourtReview(
                VALID_ID,
                VALID_COURT_ID,
                VALID_USER_ID,
                invalidRating,
                VALID_CONTENT,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 리뷰내용이_null이면_예외가_발생한다() {
        CourtReview review = new CourtReview(
                VALID_ID,
                VALID_COURT_ID,
                VALID_USER_ID,
                VALID_RATING,
                null,
                VALID_CREATED_AT,
                VALID_UPDATED_AT,
                VALID_IS_DELETED
        );

        assertThatThrownBy(review::validate)
                .isInstanceOf(IllegalArgumentException.class);
    }
}