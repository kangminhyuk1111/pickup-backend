package core.pickupbackend.auth.application.service;

import core.pickupbackend.auth.application.in.LoginUseCase;
import core.pickupbackend.auth.application.in.LogoutUseCase;
import core.pickupbackend.auth.application.in.TokenRefreshUseCase;
import core.pickupbackend.auth.application.out.JwtRepository;
import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.request.LoginCommand;
import core.pickupbackend.auth.dto.request.LogoutCommand;
import core.pickupbackend.auth.provider.TokenProvider;
import core.pickupbackend.global.exception.ApplicationException;
import core.pickupbackend.global.exception.ErrorCode;
import core.pickupbackend.member.domain.Member;
import core.pickupbackend.member.application.service.DefaultMemberService;
import core.pickupbackend.member.application.service.DefaultPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements LoginUseCase, LogoutUseCase, TokenRefreshUseCase {

    private static final String KET_PREFIX = "jwt:access:";

    private final DefaultPasswordService passwordService;
    private final DefaultMemberService memberService;
    private final JwtService jwtService;
    private final JwtRepository jwtRepository;
    private final TokenProvider tokenProvider;

    public AuthService(final DefaultPasswordService passwordService, final DefaultMemberService memberService, final JwtService jwtService, final JwtRepository jwtRepository, final TokenProvider tokenProvider) {
        this.passwordService = passwordService;
        this.memberService = memberService;
        this.jwtService = jwtService;
        this.jwtRepository = jwtRepository;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @Transactional
    public AuthCredential login(final LoginCommand loginCommand) {
        if (loginCommand == null || loginCommand.email() == null || loginCommand.password() == null) {
            throw new ApplicationException(ErrorCode.INVALID_INPUT_VALUE);
        }

        final Member member = memberService.getMemberByEmail(loginCommand.email());
        final boolean matches = passwordService.matches(loginCommand.password(), member.getPassword());

        if (!matches) {
            throw new ApplicationException(ErrorCode.PASSWORD_NOT_MATCHES);
        }

        final AuthCredential authCredential = jwtService.generateAuthCredential(member.getEmail(), member.getId());

        jwtRepository.save(authCredential);

        return authCredential;
    }

    @Override
    @Transactional
    public void logout(final String accessToken, final LogoutCommand logoutCommand) {
        if (accessToken == null || logoutCommand.email() == null) {
            throw new ApplicationException(ErrorCode.INVALID_INPUT_VALUE);
        }

        memberService.getMemberByEmail(logoutCommand.email());

        jwtRepository.delete(accessToken);
    }

    @Override
    public AuthCredential refreshToken(final String refreshToken) {
        if (!jwtService.isValidToken(refreshToken)) {
            throw new ApplicationException(ErrorCode.INVALID_REFRESH_TOKEN);
        }

        final String email = tokenProvider.extractEmailFromToken(refreshToken);
        final Long userId = tokenProvider.extractUserIdFromToken(refreshToken);
        final String jti = tokenProvider.extractJtiFromToken(refreshToken);

        jwtRepository.delete(jti);

        final AuthCredential authCredential = jwtService.generateAuthCredential(email, userId);

        jwtRepository.save(authCredential);

        return authCredential;
    }
}
