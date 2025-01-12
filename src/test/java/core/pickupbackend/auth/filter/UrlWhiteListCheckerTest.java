package core.pickupbackend.auth.filter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class UrlWhiteListCheckerTest {

    private UrlWhiteListChecker whiteListChecker = new UrlWhiteListChecker();

    @Test
    void 메소드_미지정_패턴은_모든_HTTP_메소드를_허용한다() {
        assertThat(whiteListChecker.isAllowedUri("/member", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/member", "POST")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/member", "PUT")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/member", "DELETE")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/member", "PATCH")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/member", "OPTIONS")).isTrue();
    }

    @Test
    void 단일_PathVariable_패턴을_정상_처리한다() {
        assertThat(whiteListChecker.isAllowedUri("/member/123", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/member/abc", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/member/456/details", "GET")).isFalse();
    }

    @Test
    void 다중_PathVariable_패턴을_정상_처리한다() {
        whiteListChecker.addPattern("/post/{postId}/comment/{commentId}", "GET", "DELETE");

        assertThat(whiteListChecker.isAllowedUri("/post/123/comment/456", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/post/123/comment/456", "DELETE")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/post/123/comment/456", "PUT")).isFalse();
        assertThat(whiteListChecker.isAllowedUri("/post/123/comments/456", "GET")).isFalse();
    }

    @Test
    void 특정_메소드만_허용된_URI는_해당_메소드만_허용한다() {
        assertThat(whiteListChecker.isAllowedUri("/auth/login", "POST")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/auth/login", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/auth/login", "PUT")).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "/unknown,GET",
            "/member/123/wrong,GET",
            "/auth/login/extra,POST",
            "/,GET",
            "member/123,GET"
    })
    void 화이트리스트에_없는_URI는_거부한다(String uri, String method) {
        assertThat(whiteListChecker.isAllowedUri(uri, method)).isFalse();
    }

    @Test
    void 와일드카드_패턴을_정상_처리한다() {
        whiteListChecker.addPattern("/api/**", "GET");
        whiteListChecker.addPattern("/static/*.jpg", "GET");

        assertThat(whiteListChecker.isAllowedUri("/api/users", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/api/users/123/posts", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/static/image.jpg", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/static/image.png", "GET")).isFalse();
    }

    @Test
    void HTTP_메소드는_대소문자를_구분하지_않는다() {
        assertThat(whiteListChecker.isAllowedUri("/auth/login", "post")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/auth/login", "POST")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/auth/login", "Post")).isTrue();
    }

    @Test
    void 빈_문자열_URI와_메소드는_거부한다() {
        assertThat(whiteListChecker.isAllowedUri("", "GET")).isFalse();
        assertThat(whiteListChecker.isAllowedUri("", "")).isFalse();
    }

    @Test
    void 특수문자가_포함된_URI를_정상_처리한다() {
        whiteListChecker.addPattern("/file/{fileName}", "GET");

        assertThat(whiteListChecker.isAllowedUri("/file/test.jpg", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/file/test#1.jpg", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/file/test%20file.jpg", "GET")).isTrue();
    }

    @Test
    void 다중_메소드_패턴을_정상_처리한다() {
        whiteListChecker.addPattern("/api/resource", "GET", "POST", "PUT", "DELETE");

        assertThat(whiteListChecker.isAllowedUri("/api/resource", "GET")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/api/resource", "POST")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/api/resource", "PUT")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/api/resource", "DELETE")).isTrue();
        assertThat(whiteListChecker.isAllowedUri("/api/resource", "PATCH")).isFalse();
    }

    @Test
    void 중복_패턴_등록시_마지막_설정이_적용된다() {
        whiteListChecker.addPattern("/api/test", "GET", "POST");
        whiteListChecker.addPattern("/api/test", "PUT");

        assertThat(whiteListChecker.isAllowedUri("/api/test", "GET")).isFalse();
        assertThat(whiteListChecker.isAllowedUri("/api/test", "POST")).isFalse();
        assertThat(whiteListChecker.isAllowedUri("/api/test", "PUT")).isTrue();
    }
}