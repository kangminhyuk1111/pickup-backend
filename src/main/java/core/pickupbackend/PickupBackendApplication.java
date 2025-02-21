package core.pickupbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class PickupBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(PickupBackendApplication.class, args);
    }
}
