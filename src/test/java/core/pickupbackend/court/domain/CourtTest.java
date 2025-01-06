package core.pickupbackend.court.domain;

import core.pickupbackend.global.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourtTest {
    @Test
    void 모든_필드를_가진_농구장_객체를_생성한다() {
        // given
        Long id = 1L;
        String name = "서울대학교 농구장";
        String location = "관악구";
        String address = "서울특별시 관악구 관악로 1";
        Double latitude = 37.459056;
        Double longitude = 126.952717;
        Integer hoops = 6;
        String surface = "아스팔트";
        Boolean lighting = true;
        Boolean parking = true;
        Double rating = 4.5;
        List<String> images = List.of("image1.jpg", "image2.jpg");
        List<String> facilities = List.of("화장실", "음수대");
        String bestTime = "저녁 6시 이후";

        // when
        Court court = new Court(id, name, location, address, latitude, longitude,
                hoops, surface, lighting, parking, rating, images, facilities, bestTime);

        // then
        assertThat(court.getId()).isEqualTo(id);
        assertThat(court.getName()).isEqualTo(name);
        assertThat(court.getLocation()).isEqualTo(location);
        assertThat(court.getAddress()).isEqualTo(address);
        assertThat(court.getLatitude()).isEqualTo(latitude);
        assertThat(court.getLongitude()).isEqualTo(longitude);
        assertThat(court.getHoops()).isEqualTo(hoops);
        assertThat(court.getSurface()).isEqualTo(surface);
        assertThat(court.getLighting()).isEqualTo(lighting);
        assertThat(court.getParking()).isEqualTo(parking);
        assertThat(court.getRating()).isEqualTo(rating);
        assertThat(court.getImages()).isEqualTo(images);
        assertThat(court.getFacilities()).isEqualTo(facilities);
        assertThat(court.getBestTime()).isEqualTo(bestTime);
    }

    @Test
    void 선택적_필드가_없는_농구장_객체를_생성한다() {
        // given
        Long id = 1L;
        String name = "서울대학교 농구장";
        String location = "관악구";
        String address = "서울특별시 관악구 관악로 1";
        Double latitude = 37.459056;
        Double longitude = 126.952717;
        Integer hoops = 6;
        String surface = "아스팔트";
        Boolean lighting = true;
        Boolean parking = true;
        Double rating = 4.5;
        List<String> images = null;
        List<String> facilities = null;
        String bestTime = null;

        // when
        Court court = new Court(id, name, location, address, latitude, longitude,
                hoops, surface, lighting, parking, rating, images, facilities, bestTime);

        // then
        assertThat(court.getImages()).isNull();
        assertThat(court.getFacilities()).isNull();
        assertThat(court.getBestTime()).isNull();
    }

    @Test
    void 농구장_이름이_null이면_예외가_발생한다() {
        // given
        String name = null;

        // when & then
        assertThatThrownBy(() -> new Court(1L, name, "관악구", "주소", 37.0, 127.0,
                6, "아스팔트", true, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("농구장 이름은 필수입니다");
    }

    @Test
    void 농구장_이름이_빈문자열이면_예외가_발생한다() {
        // given
        String name = "   ";

        // when & then
        assertThatThrownBy(() -> new Court(1L, name, "관악구", "주소", 37.0, 127.0,
                6, "아스팔트", true, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("농구장 이름은 필수입니다");
    }

    @Test
    void 지역명이_null이면_예외가_발생한다() {
        // given
        String location = null;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", location, "주소", 37.0, 127.0,
                6, "아스팔트", true, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("지역명은 필수입니다");
    }

    @Test
    void 주소가_null이면_예외가_발생한다() {
        // given
        String address = null;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", "관악구", address, 37.0, 127.0,
                6, "아스팔트", true, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("주소는 필수입니다");
    }

    @Test
    void 위도가_범위를_벗어나면_예외가_발생한다() {
        // given
        Double latitude = 91.0;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", "관악구", "주소", latitude, 127.0,
                6, "아스팔트", true, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("위도는 -90.000000에서 90.000000 사이여야 합니다");
    }

    @Test
    void 경도가_범위를_벗어나면_예외가_발생한다() {
        // given
        Double longitude = 181.0;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", "관악구", "주소", 37.0, longitude,
                6, "아스팔트", true, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("경도는 -180.000000에서 180.000000 사이여야 합니다");
    }

    @Test
    void 골대_개수가_0이하면_예외가_발생한다() {
        // given
        Integer hoops = 0;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", "관악구", "주소", 37.0, 127.0,
                hoops, "아스팔트", true, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("골대 개수는 0보다 커야 합니다");
    }

    @Test
    void 바닥_재질이_null이면_예외가_발생한다() {
        // given
        String surface = null;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", "관악구", "주소", 37.0, 127.0,
                6, surface, true, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("바닥 재질은 필수입니다");
    }

    @Test
    void 조명_여부가_null이면_예외가_발생한다() {
        // given
        Boolean lighting = null;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", "관악구", "주소", 37.0, 127.0,
                6, "아스팔트", lighting, true, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("조명 여부는 필수입니다");
    }

    @Test
    void 주차_가능_여부가_null이면_예외가_발생한다() {
        // given
        Boolean parking = null;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", "관악구", "주소", 37.0, 127.0,
                6, "아스팔트", true, parking, 4.5, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("주차 가능 여부는 필수입니다");
    }

    @Test
    void 평점이_범위를_벗어나면_예외가_발생한다() {
        // given
        Double rating = 5.1;

        // when & then
        assertThatThrownBy(() -> new Court(1L, "농구장", "관악구", "주소", 37.0, 127.0,
                6, "아스팔트", true, true, rating, null, null, null))
                .isInstanceOf(ApplicationException.class)
                .hasMessage("평점은 0.000000에서 5.000000 사이여야 합니다");
    }
}