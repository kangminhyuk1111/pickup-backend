package core.pickupbackend.auth.application.out;

import core.pickupbackend.auth.domain.AuthCredential;

public interface JwtRepository {
    void save(AuthCredential authCredential);

    AuthCredential findByJti(final String jti);

    void delete(final String accessToken);
}
