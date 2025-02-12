package core.pickupbackend.member.application.in;

import core.pickupbackend.member.dto.request.CheckEmailDuplicateRequest;

public interface EmailDuplicateCheckUseCase {
    boolean checkDuplicateEmail(CheckEmailDuplicateRequest dto);
}
