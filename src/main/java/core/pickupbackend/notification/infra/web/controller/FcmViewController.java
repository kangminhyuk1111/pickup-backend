package core.pickupbackend.notification.infra.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notification")
public class FcmViewController {
    @GetMapping
    public String fcmPage() {
        return "fcm";
    }
}
