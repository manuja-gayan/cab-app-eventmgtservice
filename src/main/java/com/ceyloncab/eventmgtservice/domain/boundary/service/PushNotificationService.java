package com.ceyloncab.eventmgtservice.domain.boundary.service;

import java.util.List;
import java.util.Map;

public interface PushNotificationService {
    void sendPushNotification(List<String> tokenList, String title, String body, Map<String,String> data);
}
