package core.pickupbackend.auth.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JjwtTokenProvider implements TokenProvider{

    private final KeyProvider keyProvider;

    public JjwtTokenProvider(final KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    @Override
    public String createToken(final String email, final String jti) {
        return Jwts.builder()
                .subject(email)
                .id(jti)
                .expiration(keyProvider.getExpiration())
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
    public Date extractExpirationTimeFromToken(final String token) {
        return this.extractClaimsFromToken(token).getExpiration();
    }

    @Override
    public String extractJtiFromToken(final String token) {
        return this.extractClaimsFromToken(token).getId();
    }
}
