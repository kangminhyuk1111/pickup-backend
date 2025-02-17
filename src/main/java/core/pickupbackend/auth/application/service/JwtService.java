package core.pickupbackend.auth.application.service;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.provider.JtiProvider;
import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.auth.infra.repostiroy.RedisJwtRepository;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final TokenProvider tokenProvider;
    private final JtiProvider jtiProvider;
    private final RedisJwtRepository jwtRepository;

    public JwtService(final TokenProvider tokenProvider, final JtiProvider jtiProvider, final RedisJwtRepository jwtRepository) {
        this.tokenProvider = tokenProvider;
        this.jtiProvider = jtiProvider;
        this.jwtRepository = jwtRepository;
    }

    public AuthCredential generateAuthCredential(String email, Long userId) {
        final String jti = jtiProvider.generateJti();

        final String accessToken = generateAccessToken(email, userId, jti);
        final String refreshToken = generateRefreshToken(email, userId, jti);

        return new AuthCredential(jti, accessToken, refreshToken);
    }

    public boolean isAlreadyLogin(String accessToken) {
        final String jti = tokenProvider.extractJtiFromToken(accessToken);
        final AuthCredential findByJti = jwtRepository.findByJti(jti);

        return findByJti != null;
    }

    private String generateAccessToken(final String email, final Long userId, final String jti) {
        return tokenProvider.createToken(email, userId, jti);
    }

    private String generateRefreshToken(final String email, final Long userId, final String jti) {
        return tokenProvider.createToken(email, userId, jti);
    }
}
