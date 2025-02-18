package core.pickupbackend.auth.provider;

import core.pickupbackend.auth.domain.type.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JjwtTokenProvider implements TokenProvider{

    public static final long ACCESS_TOKEN_EXPIRE_TIME = 60 * 60 * 1000L; // 1시간
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 14 * 24 * 60 * 60 * 1000L; // 14일

    private final KeyProvider keyProvider;

    public JjwtTokenProvider(final KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    @Override
    public String createToken(final String email, final Long userId, final String jti, TokenType type) {
        Date expiration = switch (type) {
            case ACCESS -> getAccessTokenExpiration();
            case REFRESH -> getRefreshTokenExpiration();
        };

        return Jwts.builder()
                .subject(email)
                .claim( "userId", userId )
                .id(jti)
                .expiration(expiration)
                .issuedAt(new Date())
                .signWith(keyProvider.getSecretKey())
                .compact();
    }

    @Override
    public Claims extractClaimsFromToken(final String token) {
        return Jwts.parser()
                .verifyWith(keyProvider.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String extractEmailFromToken(final String token) {
        return this.extractClaimsFromToken(token).getSubject();
    }

    @Override
    public Long extractUserIdFromToken(final String token) {
        return this.extractClaimsFromToken(token).get("userId", Long.class);
    }

    @Override
    public Date extractExpirationTimeFromToken(final String token) {
        return this.extractClaimsFromToken(token).getExpiration();
    }

    @Override
    public String extractJtiFromToken(final String token) {
        return this.extractClaimsFromToken(token).getId();
    }

    @Override
    public Boolean validateToken(final String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(keyProvider.getSecretKey()).build().parseSignedClaims(token).getPayload();

        return !claims.getExpiration().before(new Date());
    }

    private Date getAccessTokenExpiration() {
        return new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME);
    }

    private Date getRefreshTokenExpiration() {
        return new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME);
    }
}
