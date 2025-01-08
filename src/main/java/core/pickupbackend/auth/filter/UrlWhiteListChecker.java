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

        //addPattern("/**");
        addPattern("/member");
        addPattern("/member/{id}","GET");
        addPattern("/auth/login","POST");
        addPattern("/matches");
        addPattern("/matches/{id}");
        addPattern("/courts");
        addPattern("/courts/{id}");
        addPattern("/member/mypage");
        addPattern("/matches/participation");
        addPattern("/participation/*");
        addPattern("/matches/participation/*");
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
