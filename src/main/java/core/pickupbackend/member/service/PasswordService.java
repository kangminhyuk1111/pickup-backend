package core.pickupbackend.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public PasswordService(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
