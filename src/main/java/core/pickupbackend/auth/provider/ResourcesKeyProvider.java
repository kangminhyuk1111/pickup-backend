package core.pickupbackend.auth.provider;

import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Component
public class ResourcesKeyProvider implements KeyProvider{

    @Value("${jwt.secretkey}")
    private String key;

    @Override
    public SecretKey getSecretKey() {
        if (key == null) {
            throw new ApplicationException(ErrorCode.KEY_NOT_INITIALIZED);
        }
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    @Override
    public Date getExpiration() {
        return new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis());
    }
}
