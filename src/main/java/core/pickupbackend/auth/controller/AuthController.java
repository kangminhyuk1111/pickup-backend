package core.pickupbackend.auth.controller;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.LoginRequestDto;
import core.pickupbackend.auth.service.AuthService;
import core.pickupbackend.auth.service.JwtService;
import core.pickupbackend.auth.service.TestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(final AuthService authService, final JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthCredential login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/login-check")
    public boolean loginCheck(@RequestBody TestDto testDto) {
        return jwtService.isAlreadyLogin(testDto);
    }
}
