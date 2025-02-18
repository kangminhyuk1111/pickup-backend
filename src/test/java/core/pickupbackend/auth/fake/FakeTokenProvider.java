package core.pickupbackend.auth.fake;

import core.pickupbackend.auth.domain.type.TokenType;
import core.pickupbackend.auth.provider.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeTokenProvider implements TokenProvider {
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L; // 1시간
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 14 * 24 * 60 * 60 * 1000L; // 14일

    private final Map<String, TokenInfo> tokens = new HashMap<>();

    record TokenInfo(String email, Long userId, String jti, Date expiration) {}

    @Override
    public String createToken(final String email, final Long userId, final String jti, final TokenType type) {
        Date expiration = switch (type) {
            case ACCESS -> getAccessTokenExpiration();
            case REFRESH -> getRefreshTokenExpiration();
        };

        String token = UUID.randomUUID().toString();
        tokens.put(token, new TokenInfo(email, userId, jti, expiration));
        return token;
    }

    @Override
    public Claims extractClaimsFromToken(String token) {
        TokenInfo info = tokens.get(token);
        if (info == null) {
            throw new IllegalArgumentException("Invalid token");
        }
        return Jwts.claims()
                .subject(info.email())
                .id(info.jti())
                .expiration(info.expiration())
                .build();
    }

    @Override
    public String extractEmailFromToken(String token) {
        return tokens.get(token).email();
    }

    @Override
    public Long extractUserIdFromToken(final String token) {
        return tokens.get(token).userId();
    }

    @Override
    public Date extractExpirationTimeFromToken(String token) {
        return tokens.get(token).expiration();
    }

    @Override
    public String extractJtiFromToken(String token) {
        return tokens.get(token).jti();
    }

    @Override
    public Boolean validateToken(final String token) {
        return !tokens.get(token).expiration.before(new Date());
    }

    private Date getAccessTokenExpiration() {
        return new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME);
    }

    private Date getRefreshTokenExpiration() {
        return new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME);
    }
}