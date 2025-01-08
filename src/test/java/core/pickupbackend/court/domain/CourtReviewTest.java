package core.pickupbackend.court.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    @DisplayName("모든 필드가 유효하면 검증을 통과한다")
    void passValidationWithValidFields() {
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

    @Nested
    @DisplayName("ID 검증")
    class ValidateId {
        @Test
        @DisplayName("ID가 null이면 예외가 발생한다")
        void throwExceptionWhenIdIsNull() {
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
        @DisplayName("ID가 양수가 아니면 예외가 발생한다")
        void throwExceptionWhenIdIsNotPositive(long invalidId) {
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
    }

    @Nested
    @DisplayName("농구장 ID 검증")
    class ValidateCourtId {
        @Test
        @DisplayName("농구장 ID가 null이면 예외가 발생한다")
        void throwExceptionWhenCourtIdIsNull() {
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
        @DisplayName("농구장 ID가 양수가 아니면 예외가 발생한다")
        void throwExceptionWhenCourtIdIsNotPositive(long invalidCourtId) {
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
    }

    @Nested
    @DisplayName("사용자 ID 검증")
    class ValidateUserId {
        @Test
        @DisplayName("사용자 ID가 null이면 예외가 발생한다")
        void throwExceptionWhenUserIdIsNull() {
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
        @DisplayName("사용자 ID가 양수가 아니면 예외가 발생한다")
        void throwExceptionWhenUserIdIsNotPositive(long invalidUserId) {
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
    }

    @Nested
    @DisplayName("평점 검증")
    class ValidateRating {
        @Test
        @DisplayName("평점이 null이면 예외가 발생한다")
        void throwExceptionWhenRatingIsNull() {
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
        @DisplayName("평점이 1-5 범위를 벗어나면 예외가 발생한다")
        void throwExceptionWhenRatingIsOutOfRange(int invalidRating) {
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
    }

    @Nested
    @DisplayName("리뷰 내용 검증")
    class ValidateContent {
        @Test
        @DisplayName("내용이 null이면 예외가 발생한다")
        void throwExceptionWhenContentIsNull() {
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
}
