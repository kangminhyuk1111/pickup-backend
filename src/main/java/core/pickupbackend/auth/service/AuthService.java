package core.pickupbackend.auth.service;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.LoginRequestDto;
import core.pickupbackend.auth.dto.LogoutRequestDto;
import core.pickupbackend.auth.repostiroy.JwtRepository;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.service.MemberService;
import core.pickupbackend.member.service.PasswordService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PasswordService passwordService;
    private final MemberService memberService;
    private final JwtService jwtService;
    private final JwtRepository jwtRepository;

    public AuthService(final PasswordService passwordService, final MemberService memberService, final JwtService jwtService, final JwtRepository jwtRepository) {
        this.passwordService = passwordService;
        this.memberService = memberService;
        this.jwtService = jwtService;
        this.jwtRepository = jwtRepository;
    }

    public AuthCredential login(final LoginRequestDto loginRequestDto) {
        final Member member = memberService.getMemberByEmail(loginRequestDto.getEmail());
        final boolean matches = passwordService.matches(loginRequestDto.getPassword(), member.getPassword());

        if(!matches) {
            throw new ApplicationException(ErrorCode.PASSWORD_NOT_MATCHES);
        }
        
        final AuthCredential authCredential = jwtService.generateAuthCredential(loginRequestDto.getEmail());

        jwtRepository.save(authCredential);
        
        return authCredential;
    }

    public void logout(final LogoutRequestDto logoutRequestDto) {
        final Member member = memberService.getMemberByEmail(logoutRequestDto.getEmail());
    }
}
