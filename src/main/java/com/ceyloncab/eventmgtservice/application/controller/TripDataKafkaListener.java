package com.ceyloncab.eventmgtservice.application.controller;

import com.ceyloncab.eventmgtservice.domain.entity.dto.kafka.*;
import com.ceyloncab.eventmgtservice.domain.service.EventService;
import com.ceyloncab.eventmgtservice.domain.utils.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "trip-data",groupId = "${kafka.consumer.groupId}", containerFactory = "multiTypeKafkaListenerContainerFactory")
public class TripDataKafkaListener {

    @Autowired
    private EventService eventService;

    @KafkaHandler
    public void handleConfirmPickup(ConfirmPickupPublishResponse confirmPickup) {
        System.out.println("Confirm Pickup received: " + confirmPickup);
        eventService.process(confirmPickup, EventType.CONFIRM_PICKUP);
    }

    @KafkaHandler
    public void handleDriverAccept(AcceptTripPublishResponse acceptTrip) {
        System.out.println("Driver Accept received: " + acceptTrip);
        eventService.process(acceptTrip, EventType.DRIVER_ACCEPT);
    }

    @KafkaHandler
    public void handleUpdateTrip(UpdateTripPublishResponse updateTrip) {
        System.out.println("Update Trip received: " + updateTrip);
        eventService.process(updateTrip, EventType.valueOf(updateTrip.getStatus()));
    }

    @KafkaHandler
    public void handleEndTrip(EndTripPublishResponse endTrip) {
        System.out.println("Update Trip received: " + endTrip);
        eventService.process(endTrip, EventType.END);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Unknown type received: " + object);
    }

}
