package core.pickupbackend.auth.fake;

import core.pickupbackend.auth.provider.KeyProvider;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class FakeKeyProvider implements KeyProvider {

    private static final String SECRET_KEY = "thisisafakesecretkeyforfaketestingpurposesonly";
    private static final long EXPIRATION_TIME = 3600000; // 1시간

    @Override
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    @Override
    public Date getExpiration() {
        return new Date(System.currentTimeMillis() + EXPIRATION_TIME);
    }
}