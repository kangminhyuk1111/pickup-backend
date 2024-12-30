package core.pickupbackend.auth.provider;

import io.jsonwebtoken.Claims;

import java.util.Date;

public interface TokenProvider {

    String createToken(String email, String jti);

    Claims extractClaimsFromToken(String token);

    String extractEmailFromToken(String token);

    Date extractExpirationTimeFromToken(String token);

    String extractJtiFromToken(String token);
}
