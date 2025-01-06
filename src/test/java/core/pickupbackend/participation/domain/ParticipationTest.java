package core.pickupbackend.participation.domain;

import core.pickupbackend.match.domain.Participation;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipationTest {

    @Test
    void 필수_파라미터로_참가신청_객체를_생성한다() {
        // given
        Long memberId = 1L;
        Long matchId = 1L;
        String status = "PENDING";
        String message = "참가 신청합니다";

        // when
        Participation participation = new Participation(memberId, matchId, status, message);

        // then
        assertThat(participation.getMemberId()).isEqualTo(memberId);
        assertThat(participation.getMatchId()).isEqualTo(matchId);
        assertThat(participation.getStatus()).isEqualTo(status);
        assertThat(participation.getMessage()).isEqualTo(message);
    }

    @Test
    void 모든_파라미터로_참가신청_객체를_생성한다() {
        // given
        Long id = 1L;
        Long memberId = 1L;
        Long matchId = 1L;
        String status = "PENDING";
        String message = "참가 신청합니다";
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();

        // when
        Participation participation = new Participation(id, memberId, matchId, status, message, createdAt, updatedAt);

        // then
        assertThat(participation.getId()).isEqualTo(id);
        assertThat(participation.getMemberId()).isEqualTo(memberId);
        assertThat(participation.getMatchId()).isEqualTo(matchId);
        assertThat(participation.getStatus()).isEqualTo(status);
        assertThat(participation.getMessage()).isEqualTo(message);
        assertThat(participation.getCreatedAt()).isEqualTo(createdAt);
        assertThat(participation.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void 사용자ID가_null이면_예외가_발생한다() {
        // given
        Long memberId = null;
        Long matchId = 1L;
        String status = "PENDING";
        String message = "참가 신청합니다";

        // when & then
        assertThatThrownBy(() -> new Participation(memberId, matchId, status, message))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("사용자 ID는 필수입니다");
    }

    @Test
    void 매칭ID가_null이면_예외가_발생한다() {
        // given
        Long memberId = 1L;
        Long matchId = null;
        String status = "PENDING";
        String message = "참가 신청합니다";

        // when & then
        assertThatThrownBy(() -> new Participation(memberId, matchId, status, message))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("매칭 ID는 필수입니다");
    }

    @Test
    void 상태값이_null이면_예외가_발생한다() {
        // given
        Long memberId = 1L;
        Long matchId = 1L;
        String status = null;
        String message = "참가 신청합니다";

        // when & then
        assertThatThrownBy(() -> new Participation(memberId, matchId, status, message))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상태값은 필수입니다");
    }

    @Test
    void 상태값이_빈문자열이면_예외가_발생한다() {
        // given
        Long memberId = 1L;
        Long matchId = 1L;
        String status = "   ";
        String message = "참가 신청합니다";

        // when & then
        assertThatThrownBy(() -> new Participation(memberId, matchId, status, message))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상태값은 필수입니다");
    }

    @Test
    void 상태값이_유효하지_않으면_예외가_발생한다() {
        // given
        Long memberId = 1L;
        Long matchId = 1L;
        String status = "INVALID_STATUS";
        String message = "참가 신청합니다";

        // when & then
        assertThatThrownBy(() -> new Participation(memberId, matchId, status, message))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 상태값입니다. 가능한 값: PENDING, ACCEPTED, REJECTED");
    }

    @Test
    void 기본_생성자로_객체를_생성한다() {
        // when
        Participation participation = new Participation();

        // then
        assertThat(participation).isNotNull();
    }

    @Test
    void 상태값은_대소문자_구분없이_검증한다() {
        // given
        Long memberId = 1L;
        Long matchId = 1L;
        String status = "pending";
        String message = "참가 신청합니다";

        // when
        Participation participation = new Participation(memberId, matchId, status, message);

        // then
        assertThat(participation.getStatus()).isEqualTo(status);
    }
}