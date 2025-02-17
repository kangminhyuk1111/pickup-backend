package core.pickupbackend.auth.application.service;

import core.pickupbackend.auth.application.in.LoginUseCase;
import core.pickupbackend.auth.application.in.LogoutUseCase;
import core.pickupbackend.auth.application.out.JwtRepository;
import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.request.LoginCommand;
import core.pickupbackend.auth.dto.request.LogoutCommand;
import core.pickupbackend.auth.infra.repostiroy.RedisJwtRepository;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.application.service.DefaultMemberService;
import core.pickupbackend.member.application.service.DefaultPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements LoginUseCase, LogoutUseCase {

    private final DefaultPasswordService passwordService;
    private final DefaultMemberService memberService;
    private final JwtService jwtService;
    private final JwtRepository jwtRepository;

    public AuthService(final DefaultPasswordService passwordService, final DefaultMemberService memberService, final JwtService jwtService, final JwtRepository jwtRepository) {
        this.passwordService = passwordService;
        this.memberService = memberService;
        this.jwtService = jwtService;
        this.jwtRepository = jwtRepository;
    }

    @Override
    @Transactional
    public AuthCredential login(final LoginCommand loginCommand) {
        final Member member = memberService.getMemberByEmail(loginCommand.email());
        final boolean matches = passwordService.matches(loginCommand.password(), member.getPassword());

        if(!matches) {
            throw new ApplicationException(ErrorCode.PASSWORD_NOT_MATCHES);
        }
        
        final AuthCredential authCredential = jwtService.generateAuthCredential(member.getEmail(), member.getId());

        jwtRepository.save(authCredential);
        
        return authCredential;
    }

    @Override
    @Transactional
    public void logout(final String accessToken,final LogoutCommand logoutCommand) {
        memberService.getMemberByEmail(logoutCommand.email());

        jwtRepository.delete(accessToken);
    }
}
