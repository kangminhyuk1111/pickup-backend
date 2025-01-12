package core.pickupbackend.notification.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FCMConfig {

    private final static Logger logger = LoggerFactory.getLogger(FCMConfig.class);

    @Value("${fcm.json.link}")
    private Resource serviceAccountJson;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {

        logger.info("Service account resource exists: {}", serviceAccountJson.exists());
        logger.info("Service account path: {}", serviceAccountJson.getURI());

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountJson.getInputStream()))
                .build();

        final FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);

        logger.info("Initialized firebase app config: {}",firebaseApp);

        return firebaseApp;
    }

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
