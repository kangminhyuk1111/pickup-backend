package core.pickupbackend.global.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LogbackConfig {

    @Value("${log.path}")
    private String logPath;

    @PostConstruct
    public void init() {
        File logDir = new File(logPath);
        File archivedDir = new File(logPath + "/archived");

        if (!logDir.exists()) {
            logDir.mkdirs();
        }
        if (!archivedDir.exists()) {
            archivedDir.mkdirs();
        }
    }
}