package core.pickupbackend.match.domain;

import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.global.exception.ApplicationMatchException;
import core.pickupbackend.member.domain.type.Level;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MatchTest {

    @Test
    void 매치_정상_생성() {
        Match match = new Match(
                "농구 경기",
                "친선 경기입니다.",
                "중앙 코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                1000,
                "공격적인 플레이 금지"
        );

        assertThat(match.getTitle()).isEqualTo("농구 경기");
        assertThat(match.getDescription()).isEqualTo("친선 경기입니다.");
        assertThat(match.getCourtName()).isEqualTo("중앙 코트");
        assertThat(match.getDistrict()).isEqualTo("서울특별시 강남구");
        assertThat(match.getLocationDetail()).isEqualTo("중앙 코트");
        assertThat(match.getDate()).isEqualTo(LocalDate.now().plusDays(1));
        assertThat(match.getTime()).isEqualTo(LocalTime.of(18, 0));
        assertThat(match.getLevel()).isEqualTo(Level.INTERMEDIATE);
        assertThat(match.getCurrentPlayers()).isEqualTo(5);
        assertThat(match.getMaxPlayers()).isEqualTo(10);
        assertThat(match.getCost()).isEqualTo(1000);
        assertThat(match.getRules()).isEqualTo("공격적인 플레이 금지");
    }

    @Test
    void 제목은_null_또는_공백일_수_없다() {
        assertThatThrownBy(() -> new Match(
                null,
                "설명",
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                10,
                5,
                1L,
                1000,
                "규칙"
        ))
                .isInstanceOf(ApplicationMatchException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.TITLE_BLANK_EXCEPTION);
    }

    @Test
    void 제목은_최대_길이를_초과할_수_없다() {
        String longTitle = "A".repeat(101);
        assertThatThrownBy(() -> new Match(
                longTitle,
                "설명",
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                1000,
                "규칙"
        ))
                .isInstanceOf(ApplicationMatchException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.TITLE_LENGTH_EXCEPTION);
    }

    @Test
    void 설명은_null_일_수_있다() {
        Match match = new Match(
                "제목",
                null,
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                1000,
                "규칙"
        );
        assertThat(match.getDescription()).isNull();
    }

    @Test
    void 설명은_최대_길이를_초과할_수_없다() {
        String longDescription = "A".repeat(2001);
        assertThatThrownBy(() -> new Match(
                "제목",
                longDescription,
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                1000,
                "규칙"
        )).isInstanceOf(ApplicationMatchException.class);
    }

    @Test
    void 코트_이름은_null_또는_공백일_수_없다() {
        assertThatThrownBy(() -> new Match(
                "제목",
                "설명",
                null,
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                1000,
                "규칙"
        )).isInstanceOf(ApplicationMatchException.class);
    }

    @Test
    void 위치는_null_또는_공백일_수_없다() {
        assertThatThrownBy(() -> new Match(
                "제목",
                "설명",
                "코트",
                null,
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                1000,
                "규칙"
        )).isInstanceOf(ApplicationMatchException.class);
    }

    @Test
    void 날짜와_시간은_null_일_수_없다() {
        assertThatThrownBy(() -> new Match(
                "제목",
                "설명",
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                null,
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                1000,
                "규칙"
        )).isInstanceOf(ApplicationMatchException.class);
    }

    @Test
    void 날짜는_과거일_수_없다() {
        assertThatThrownBy(() -> new Match(
                "제목",
                "설명",
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().minusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                1000,
                "규칙"
        )).isInstanceOf(ApplicationMatchException.class);
    }

    @Test
    void 현재_플레이어는_음수일_수_없다() {
        assertThatThrownBy(() -> new Match(
                "제목",
                "설명",
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                -1,
                10,
                1L,
                1000,
                "규칙"
        )).isInstanceOf(ApplicationMatchException.class);
    }

    @Test
    void 최대_플레이어는_0_이하일_수_없다() {
        assertThatThrownBy(() -> new Match(
                "제목",
                "설명",
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                0,
                1L,
                1000,
                "규칙"
        )).isInstanceOf(ApplicationMatchException.class);
    }

    @Test
    void 비용은_음수일_수_없다() {
        assertThatThrownBy(() -> new Match(
                "제목",
                "설명",
                "코트",
                "서울특별시 강남구",
                "중앙 코트",
                LocalDate.now().plusDays(1),
                LocalTime.of(18, 0),
                Level.INTERMEDIATE,
                5,
                10,
                1L,
                -1000,
                "규칙"
        )).isInstanceOf(ApplicationMatchException.class);
    }
}