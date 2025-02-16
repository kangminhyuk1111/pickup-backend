package core.pickupbackend.auth.application.in;

import core.pickupbackend.auth.domain.AuthCredential;
import core.pickupbackend.auth.dto.request.LoginCommand;

public interface LoginUseCase {
    AuthCredential login(LoginCommand loginCommand);
}
