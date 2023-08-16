package com.ceyloncab.eventmgtservice.domain.service;

import com.ceyloncab.eventmgtservice.domain.boundary.service.PushNotificationService;
import com.ceyloncab.eventmgtservice.domain.entity.UserTokenEntity;
import com.ceyloncab.eventmgtservice.domain.entity.dto.kafka.*;
import com.ceyloncab.eventmgtservice.domain.utils.EventType;
import com.ceyloncab.eventmgtservice.external.repository.UserTokenRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class EventService {

    @Autowired
    UserTokenRepository userTokenRepository;
    @Autowired
    PushNotificationService pushNotificationService;

    ObjectMapper objMapper = new ObjectMapper();

//    public void handleVehicleDataTopic(TripPriceVehiclePublishResponse request) {
//
//        //TODO: add switch-case to handle by event type
//        List<String> users = request.getUserInfo().getUsers();
//        List<String> fcmTokens = new ArrayList<>();
//        for (String user : users) {
//            Optional<UserTokenEntity> token = userTokenRepository.findByUserId(user);
//            if(token.isPresent()){
//                fcmTokens.add(token.get().getFcmToken());
//            }else {
//                log.error("User Token not found for userId:{}",user);
//            }
//        }
//
//        Map<String,String> data = new HashMap<>();
//        try {
//            data.put("data", objMapper.writeValueAsString(request));
//        }catch (JsonProcessingException e) {
//            log.error("Error in object parsing to string.Error:{}",e.getMessage(),e);
//        }
//        pushNotificationService.sendPushNotification(fcmTokens,request.getStatus(),request.getStatus(),data);
//    }

    public void process(Object request, EventType eventType) {

        List<String> tokens = new ArrayList<>();
        Map<String,String> data = new HashMap<>();
        switch (eventType){
            case VEHICLE_TRIP_PRICE:
                TripPriceVehiclePublishResponse tripPrice = (TripPriceVehiclePublishResponse) request;
                tokens = getFCMTokensFromUsers(tripPrice.getUserInfo().getUsers());
                //remove userInfo object from response
                tripPrice.setUserInfo(null);
                try {
                    data.put("data", objMapper.writeValueAsString(tripPrice));
                }catch (JsonProcessingException e) {
                    log.error("Error in object parsing to string.Error:{}",e.getMessage(),e);
                }
                pushNotificationService.sendPushNotification(tokens,tripPrice.getStatus(),tripPrice.getStatus(),data);
                break;
            case DRIVER_ACCEPT:
                AcceptTripPublishResponse acceptTrip = (AcceptTripPublishResponse) request;
                tokens = getFCMTokensFromUsers(acceptTrip.getUserInfo().getUsers());
                //remove userInfo object from response
                acceptTrip.setUserInfo(null);
                try {
                    data.put("data", objMapper.writeValueAsString(acceptTrip));
                }catch (JsonProcessingException e) {
                    log.error("Error in object parsing to string.Error:{}",e.getMessage(),e);
                }
                pushNotificationService.sendPushNotification(tokens,acceptTrip.getStatus(),acceptTrip.getStatus(),data);
                break;
            case DRIVER_CANCELLED:
            case CUSTOMER_CANCELLED:
                UpdateTripPublishResponse updateTrip = (UpdateTripPublishResponse) request;
                tokens = getFCMTokensFromUsers(updateTrip.getUserInfo().getUsers());
                //remove userInfo object from response
                updateTrip.setUserInfo(null);
                try {
                    data.put("data", objMapper.writeValueAsString(updateTrip));
                }catch (JsonProcessingException e) {
                    log.error("Error in object parsing to string.Error:{}",e.getMessage(),e);
                }
                pushNotificationService.sendPushNotification(tokens,updateTrip.getStatus(),updateTrip.getStatus(),data);
                break;
            case END:
                EndTripPublishResponse endTrip = (EndTripPublishResponse) request;
                tokens = getFCMTokensFromUsers(endTrip.getUserInfo().getUsers());
                //remove userInfo object from response
                endTrip.setUserInfo(null);
                try {
                    data.put("data", objMapper.writeValueAsString(endTrip));
                }catch (JsonProcessingException e) {
                    log.error("Error in object parsing to string.Error:{}",e.getMessage(),e);
                }
                pushNotificationService.sendPushNotification(tokens,endTrip.getStatus(),endTrip.getStatus(),data);
                break;
            case CONFIRM_PICKUP:
            default:
                ConfirmPickupPublishResponse confirmPickup = (ConfirmPickupPublishResponse) request;
                tokens = getFCMTokensFromUsers(confirmPickup.getUserInfo().getUsers());
                //remove userInfo object from response
                confirmPickup.setUserInfo(null);
                try {
                    data.put("data", objMapper.writeValueAsString(confirmPickup));
                }catch (JsonProcessingException e) {
                    log.error("Error in object parsing to string.Error:{}",e.getMessage(),e);
                }
                pushNotificationService.sendPushNotification(tokens,confirmPickup.getStatus(),confirmPickup.getStatus(),data);
                break;
        }
    }

    private List<String> getFCMTokensFromUsers(List<String> users){
        List<String> fcmTokens = new ArrayList<>();
        for (String user : users) {
            Optional<UserTokenEntity> token = userTokenRepository.findByUserId(user);
            if(token.isPresent()){
                fcmTokens.add(token.get().getFcmToken());
            }else {
                log.error("User Token not found for userId:{}",user);
            }
        }
        return fcmTokens;
    }
}
