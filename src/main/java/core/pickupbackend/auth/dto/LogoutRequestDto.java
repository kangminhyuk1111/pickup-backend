package core.pickupbackend.auth.dto;

public class LogoutRequestDto {

    private final String email;

    public LogoutRequestDto(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
