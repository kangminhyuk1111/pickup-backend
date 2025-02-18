package core.pickupbackend.auth.application.in;

import core.pickupbackend.auth.domain.AuthCredential;

public interface TokenRefreshUseCase {
    AuthCredential refreshToken(String refreshToken);
}
