package core.pickupbackend.auth.service;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.request.LoginRequest;
import core.pickupbackend.auth.dto.request.LogoutRequest;
import core.pickupbackend.auth.repostiroy.JwtRepository;
import core.pickupbackend.device.application.service.DefaultDeviceService;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.application.service.DefaultMemberService;
import core.pickupbackend.member.application.service.DefaultPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final DefaultPasswordService passwordService;
    private final DefaultMemberService memberService;
    private final DefaultDeviceService deviceService;
    private final JwtService jwtService;
    private final JwtRepository jwtRepository;

    public AuthService(final DefaultPasswordService passwordService, final DefaultMemberService memberService, final DefaultDeviceService deviceService, final JwtService jwtService, final JwtRepository jwtRepository) {
        this.passwordService = passwordService;
        this.memberService = memberService;
        this.deviceService = deviceService;
        this.jwtService = jwtService;
        this.jwtRepository = jwtRepository;
    }

    @Transactional
    public AuthCredential login(final LoginRequest loginRequestDto) {
        final Member member = memberService.getMemberByEmail(loginRequestDto.email());
        final boolean matches = passwordService.matches(loginRequestDto.password(), member.getPassword());

        if(!matches) {
            throw new ApplicationException(ErrorCode.PASSWORD_NOT_MATCHES);
        }
        
        final AuthCredential authCredential = jwtService.generateAuthCredential(loginRequestDto.email());

        jwtRepository.save(authCredential);
        
        return authCredential;
    }

    @Transactional
    public void logout(final String accessToken,final LogoutRequest logoutRequestDto) {
        final Member member = memberService.getMemberByEmail(logoutRequestDto.email());

        jwtRepository.delete(accessToken);
    }
}
