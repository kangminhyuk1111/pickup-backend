package core.pickupbackend.auth.infra.repostiroy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.pickupbackend.auth.application.out.JwtRepository;
import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisJwtRepository implements JwtRepository {

    private static final Duration TOKEN_EXPIRATION = Duration.ofHours(1);
    private static final String KEY_PREFIX = "jwt:access:";

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisJwtRepository(final StringRedisTemplate redisTemplate, final ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    // 저장
    public void save(AuthCredential authCredential) {
        final String tokenJson = generateTokenJson(authCredential);
        redisTemplate.opsForValue().set(KEY_PREFIX + authCredential.jti(), tokenJson, TOKEN_EXPIRATION);
    }

    public AuthCredential findByJti(final String jti) {
        final String key = KEY_PREFIX + jti;
        final String value = redisTemplate.opsForValue().get(key);

        if (value == null) {
            throw new ApplicationException(ErrorCode.TOKEN_NOT_FOUND);
        }

        try {
            return objectMapper.readValue(value, AuthCredential.class);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(ErrorCode.BLANK_EXCEPTION);
        }
    }

    public void delete(final String token) {
        final AuthCredential authCredential = findByJti(token);
        redisTemplate.delete(authCredential.jti());
    }

    private String generateTokenJson(final AuthCredential authCredential) {
        try {
            return objectMapper.writeValueAsString(authCredential);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(ErrorCode.BLANK_EXCEPTION);
        }
    }
}
