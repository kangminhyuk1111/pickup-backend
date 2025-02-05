package core.pickupbackend.auth.controller;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.LoginRequest;
import core.pickupbackend.auth.dto.LogoutRequest;
import core.pickupbackend.auth.service.AuthService;
import core.pickupbackend.device.service.DeviceService;
import core.pickupbackend.global.common.response.BaseResponse;
import core.pickupbackend.global.common.code.StatusCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public BaseResponse<AuthCredential> login(@RequestBody LoginRequest loginRequestDto) {
        logger.debug("/login request: {}", loginRequestDto);
        final AuthCredential authCredential = authService.login(loginRequestDto);
        return new BaseResponse<>(StatusCode.SUCCESS, authCredential);
    }

    @Operation(summary = "로그아웃")
    @PostMapping("/logout")
    public BaseResponse<Void> logout(@RequestHeader("Authorization") String accessToken, @RequestBody LogoutRequest logoutRequestDto) {
        logger.debug("/logout request: {}", logoutRequestDto);
        authService.logout(accessToken, logoutRequestDto);
        return new BaseResponse<>(StatusCode.SUCCESS, null);
    }
}
