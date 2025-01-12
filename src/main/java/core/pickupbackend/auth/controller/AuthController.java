package core.pickupbackend.auth.controller;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.LoginRequest;
import core.pickupbackend.auth.dto.LogoutRequest;
import core.pickupbackend.auth.service.AuthService;
import core.pickupbackend.device.service.DeviceService;
import core.pickupbackend.global.common.response.BaseResponse;
import core.pickupbackend.global.common.code.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @ResponseBody
    public BaseResponse<AuthCredential> login(@RequestBody LoginRequest loginRequestDto) {
        logger.debug("/login request: {}", loginRequestDto);
        return new BaseResponse<>(StatusCode.SUCCESS, authService.login(loginRequestDto));
    }

    @PostMapping("/logout")
    public BaseResponse<Void> logout(@RequestHeader("Authorization") String accessToken, @RequestBody LogoutRequest logoutRequestDto) {
        logger.debug("/logout request: {}", logoutRequestDto);
        authService.logout(accessToken, logoutRequestDto);
        return new BaseResponse<>(StatusCode.SUCCESS, null);
    }
}
