package core.pickupbackend.match.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MatchServiceTest {

//    private final MatchRepository matchRepository = new FakeMatchRepository();
//    private final TokenProvider tokenProvider = new FakeTokenProvider();
//    private final MatchService matchService = new MatchService(matchRepository);
//
//    @Test
//    void 매치생성_성공() {
//        // given
//        CreateMatchDto createMatchDto = createMatchDto();
//
//        // when
//        Match result = matchService.createMatch(createMatchDto);
//
//        // then
//        assertThat(result.getId()).isEqualTo(1L);
//        assertThat(result.getHostId()).isEqualTo(createMatchDto().getHostId());
//        assertThat(result.getStatus()).isEqualTo(MatchStatus.OPEN);
//        assertThat(result.getCreatedAt()).isNotNull();
//        assertThat(result.getUpdatedAt()).isNotNull();
//    }
//
//    @Test
//    void ID로_매치찾기_성공() {
//        // given
//        Match savedMatch = matchService.createMatch(createMatchDto());
//
//        // when
//        Match result = matchService.findById(savedMatch.getId());
//
//        // then
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isEqualTo(savedMatch.getId());
//        assertThat(result.getHostId()).isEqualTo(savedMatch.getHostId());
//    }
//
//    @Test
//    void 존재하지않는_ID로_매치찾기_실패() {
//        // given
//        Long nonExistentId = 999L;
//
//        // when & then
//        assertThatThrownBy(() -> matchService.findById(nonExistentId)).isInstanceOf(ApplicationMatchException.class);
//    }
//
//    @Test
//    void 전체_매치찾기_매치가_있는경우() {
//        // given
//        Match match1 = matchService.createMatch(createMatchDto());
//        Match match2 = matchService.createMatch(createMatchDto());
//
//        // when
//        List<Match> results = matchService.findAll();
//
//        // then
//        assertThat(results).hasSize(2);
//        assertThat(results).extracting("id").containsExactly(1L, 2L);
//    }
//
//    @Test
//    void 전체_매치찾기_매치가_없는경우() {
//        // when
//        List<Match> results = matchService.findAll();
//
//        // then
//        assertThat(results).isEmpty();
//    }
//
//    private CreateMatchDto createMatchDto() {
//        return new CreateMatchDto(
//                "테스트 매치",          // title
//                "매치 설명입니다",       // description
//                "테스트 코트",          // courtName
//                "서울시 강남구",         // location
//                LocalDate.now(),       // date
//                LocalTime.of(14, 0),  // time
//                Level.BEGINNER,        // level
//                1,                     // currentPlayers
//                4,                     // maxPlayers
//                1L,                     // hostId
//                10000,                 // cost
//                "매치 규칙입니다"         // rules
//        );
//    }
}