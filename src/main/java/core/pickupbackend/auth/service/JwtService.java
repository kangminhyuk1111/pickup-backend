package core.pickupbackend.auth.service;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.provider.JtiProvider;
import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.auth.repostiroy.JwtRepository;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final TokenProvider tokenProvider;
    private final JtiProvider jtiProvider;
    private final JwtRepository jwtRepository;

    public JwtService(final TokenProvider tokenProvider, final JtiProvider jtiProvider, final JwtRepository jwtRepository, final JwtRepository jwtRepository1) {
        this.tokenProvider = tokenProvider;
        this.jtiProvider = jtiProvider;
        this.jwtRepository = jwtRepository1;
    }

    public AuthCredential generateAuthCredential(String email) {
        final String jti = jtiProvider.generateJti();

        final String accessToken = generateAccessToken(email, jti);
        final String refreshToken = generateRefreshToken(email, jti);

        return new AuthCredential(jti, accessToken, refreshToken);
    }

    public boolean isAlreadyLogin(TestDto testDto) {
        final String jti = tokenProvider.extractJtiFromToken(testDto.getAccessToken());
        final AuthCredential findByJti = jwtRepository.findByJti(jti);

        return findByJti != null;
    }

    private String generateAccessToken(final String email, final String jti) {
        return tokenProvider.createToken(email, jti);
    }

    private String generateRefreshToken(final String email, final String jti) {
        return tokenProvider.createToken(email, jti);
    }
}
