package core.pickupbackend.push.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FCMConfig {

    @Value("${fcm.json.link}")
    private String firebaseFile;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        FileInputStream aboutFirebaseFile = new FileInputStream(String.valueOf(ResourceUtils.getFile(firebaseFile)));

        FirebaseOptions options = FirebaseOptions
                .builder()
                .setCredentials(GoogleCredentials.fromStream(aboutFirebaseFile))
                .build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
}
