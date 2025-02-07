package core.pickupbackend.match.application.in;

public interface DeleteMatchUseCase {
    void deleteById(String accessToken, Long id);
}
