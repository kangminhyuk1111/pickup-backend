package core.pickupbackend.auth.application.in;

import core.pickupbackend.auth.dto.request.LogoutCommand;

public interface LogoutUseCase {
    void logout(String accessToken, LogoutCommand logoutCommand);
}
