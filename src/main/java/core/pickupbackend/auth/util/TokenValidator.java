package core.pickupbackend.auth.util;

import core.pickupbackend.auth.provider.KeyProvider;
import core.pickupbackend.auth.repostiroy.JwtRepository;

public class TokenValidator {

    private final JwtRepository jwtRepository;
    private final KeyProvider keyProvider;

    public TokenValidator(final JwtRepository jwtRepository, final KeyProvider keyProvider) {
        this.jwtRepository = jwtRepository;
        this.keyProvider = keyProvider;
    }


}
