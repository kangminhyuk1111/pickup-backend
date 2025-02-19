package core.pickupbackend.auth.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

@Component
public class UrlWhiteListChecker {

    private final PathMatcher pathMatcher;
    private final Map<String, Set<String>> whitelistPatterns;

    public UrlWhiteListChecker() {
        this.pathMatcher = new AntPathMatcher();
        this.whitelistPatterns = new HashMap<>();

        // Public APIs (No authentication required)
        addPattern("/auth/**");                // 인증 관련 모든 엔드포인트
        addPattern("/public/**");              // 공개 API 엔드포인트

        // Member APIs
        addPattern("/member", "GET", "POST");  // 회원 목록 조회 및 생성
        addPattern("/member/{id}", "GET");  // 특정 회원 정보 조회

        // Match APIs
        addPattern("/matches", "GET");         // 매치 목록 조회
        addPattern("/matches/{id}/**", "GET"); // 특정 매치 정보 조회
        addPattern("/matches/participation/**", "GET"); // 참가 정보 조회
        addPattern("/matches/accept","POST");
        addPattern("/matches/rejected","POST");

        // Court APIs
        addPattern("/courts/**", "GET");       // 코트 관련 모든 조회 API

        // Participation APIs
        addPattern("/participation/{id}/**");  // 참가 관련 모든 API

        // Device & Push Notification APIs
        addPattern("/device/**");              // 디바이스 관련 모든 API
        addPattern("/push/**");                // 푸시 알림 관련 모든 API

        // Swagger Documentation
        addSwaggerPatterns();

        // fcm
        addPattern("/notification/**");

        // mail
        addPattern("/mail/**");
    }

    private void addSwaggerPatterns() {
        String[] swaggerPaths = {
                "/swagger-ui/**",           // Swagger UI 리소스
                "/swagger-resources/**",     // Swagger 설정 및 리소스
                "/v1/api-docs/**",          // OpenAPI 스펙 문서
                "/swagger-ui/index.html",    // Swagger UI 메인 페이지
                "/webjars/**"               // Swagger UI 웹 리소스
        };

        for (String path : swaggerPaths) {
            addPattern(path);
        }
    }

    public void addPattern(String pattern, String... methods) {
        if (methods.length == 0) {
            whitelistPatterns.put(pattern, Collections.emptySet());
            return;
        }

        Set<String> methodSet = new HashSet<>(Arrays.asList(methods));

        whitelistPatterns.put(pattern, methodSet);
    }

    public boolean isAllowedUri(String uri, String method) {
        String upperMethod = method.toUpperCase();

        return whitelistPatterns.entrySet().stream()
                .filter(entry -> pathMatcher.match(entry.getKey(), uri))
                .anyMatch(entry -> {
                    Set<String> allowedMethods = entry.getValue();
                    return allowedMethods.isEmpty() || allowedMethods.contains(upperMethod);
                });
    }
}
