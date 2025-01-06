package core.pickupbackend.match.controller;

import core.pickupbackend.match.service.ParticipationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/participation")
public class ParticipationController {

    private final ParticipationService participationService;

    public ParticipationController(final ParticipationService participationService) {
        this.participationService = participationService;
    }
}
