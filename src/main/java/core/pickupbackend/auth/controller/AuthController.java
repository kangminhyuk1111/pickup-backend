package core.pickupbackend.auth.controller;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.request.LoginRequest;
import core.pickupbackend.auth.dto.request.LogoutRequest;
import core.pickupbackend.auth.dto.response.LoginResponse;
import core.pickupbackend.auth.service.AuthService;
import core.pickupbackend.global.common.response.BaseResponse;
import core.pickupbackend.global.common.code.StatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "로그인, 로그아웃 API")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    @ResponseBody
    public LoginResponse login(@RequestBody LoginRequest loginRequestDto) {
        logger.debug("/login request: {}", loginRequestDto);
        final AuthCredential authCredential = authService.login(loginRequestDto);
        return LoginResponse.from(authCredential);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String accessToken, @RequestBody LogoutRequest logoutRequestDto) {
        logger.debug("/logout request: {}", logoutRequestDto);
        authService.logout(accessToken, logoutRequestDto);
    }
}
