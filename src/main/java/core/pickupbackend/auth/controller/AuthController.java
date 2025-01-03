package core.pickupbackend.auth.controller;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.LoginRequestDto;
import core.pickupbackend.auth.dto.LogoutRequestDto;
import core.pickupbackend.auth.service.AuthService;
import core.pickupbackend.auth.service.JwtService;
import core.pickupbackend.match.domain.Match;
import core.pickupbackend.match.dto.CreateMatchDto;
import core.pickupbackend.match.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(final AuthService authService, final JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @ResponseBody
    public AuthCredential login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken,
                       @RequestBody LogoutRequestDto logoutRequestDto) {
        authService.logout(accessToken, logoutRequestDto);
    }
}
