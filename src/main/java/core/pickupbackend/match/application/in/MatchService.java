package core.pickupbackend.match.application.in;

public interface MatchService extends
        CreateMatchUseCase,
        FindAllMatchesUseCase,
        UpdateMatchUseCase,
        FindMatchByIdUseCase,
        DeleteMatchUseCase,
        FindMatchParticipationUseCase,
        MatchAcceptUseCase,
        MatchRejectedUseCase
{
}
