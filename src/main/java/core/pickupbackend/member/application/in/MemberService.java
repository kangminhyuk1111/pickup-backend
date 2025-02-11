package core.pickupbackend.member.application.in;

public interface MemberService extends
        GetAllMemberUseCase,
        CreateMemberUseCase,
        GetMemberByIdUseCase,
        GetMemberByEmailUseCase,
        DeleteMemberByIdUseCase,
        UpdateByIdUseCase { }
