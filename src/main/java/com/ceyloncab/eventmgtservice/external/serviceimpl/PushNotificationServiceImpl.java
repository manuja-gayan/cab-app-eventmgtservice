package com.ceyloncab.eventmgtservice.external.serviceimpl;

import com.ceyloncab.eventmgtservice.domain.boundary.service.PushNotificationService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**;
 * HI
 */
@Slf4j
@Service
public class PushNotificationServiceImpl implements PushNotificationService {
    @Value("${firebase.configuration-file}")
    private String firebaseConfigPath;

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            log.error("Error cire while initializing the firebase.Error:{}",e.getMessage());
        }
    }
    @Override
    public void sendPushNotification(List<String> tokenList, String title, String body, Map<String,String> data) {

        try {
            MulticastMessage message = MulticastMessage.builder()
                    .setNotification(Notification.builder()
                            .setTitle(title)
                            .setBody(body)
                            .build())
                    .putAllData(data)
                    .addAllTokens(tokenList)
                    .build();
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            log.info("Successfully send the notification to users:{}",response.getResponses());
        }catch (FirebaseMessagingException ex){
            log.error("Error occurred while sending push notification.Error:{}",ex.getMessage(),ex);
        }
    }
}
