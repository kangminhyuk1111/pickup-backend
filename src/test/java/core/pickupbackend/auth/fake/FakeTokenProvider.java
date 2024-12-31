package core.pickupbackend.auth.fake;

import core.pickupbackend.auth.provider.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FakeTokenProvider implements TokenProvider {
    private final Map<String, TokenInfo> tokens = new HashMap<>();

    record TokenInfo(String email, String jti, Date expiration) {}

    @Override
    public String createToken(String email, String jti) {
        String token = UUID.randomUUID().toString();
        tokens.put(token, new TokenInfo(email, jti,
                new Date(System.currentTimeMillis() + 3600000)));
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
    public Date extractExpirationTimeFromToken(String token) {
        return tokens.get(token).expiration();
    }

    @Override
    public String extractJtiFromToken(String token) {
        return tokens.get(token).jti();
    }
}