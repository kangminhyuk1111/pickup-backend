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

        addPattern("/**");
        addPattern("/member");
        addPattern("/member/{id}","GET");
        addPattern("/auth/login");
        addPattern("/matches/*","GET");
        addPattern("/matches","GET");
        addPattern("/courts","GET");
        addPattern("/courts/{id}","GET");
        addPattern("/matches/participation","GET");
        addPattern("/participation/*");
        addPattern("/matches/participation/*");
        addPattern("/device");
        addPattern("/push/**");

        // Swagger UI 관련 패턴들
        addPattern("/swagger-ui/**");           // Swagger UI 리소스들
        addPattern("/swagger-resources/**");    // Swagger 설정 및 리소스
        addPattern("/v1/api-docs/**");         // OpenAPI 스펙 문서
        addPattern("/swagger-ui/index.html");        // Swagger UI 메인 페이지
        addPattern("/webjars/**");            // Swagger UI에서 사용하는 웹 리소스
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
