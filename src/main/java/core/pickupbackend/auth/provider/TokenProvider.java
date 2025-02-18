package core.pickupbackend.auth.provider;

import core.pickupbackend.auth.domain.type.TokenType;
import io.jsonwebtoken.Claims;

import java.util.Date;

public interface TokenProvider {

    String createToken(String email, Long userId, String jti, TokenType type);

    Claims extractClaimsFromToken(String token);

    String extractEmailFromToken(String token);

    Long extractUserIdFromToken(String token);

    Date extractExpirationTimeFromToken(String token);

    String extractJtiFromToken(String token);

    Boolean validateToken(String token);
}
