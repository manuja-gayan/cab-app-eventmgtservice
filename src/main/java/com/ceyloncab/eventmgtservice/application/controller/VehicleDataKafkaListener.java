package com.ceyloncab.eventmgtservice.application.controller;

import com.ceyloncab.eventmgtservice.domain.entity.dto.kafka.TripPriceVehiclePublishResponse;
import com.ceyloncab.eventmgtservice.domain.service.EventService;
import com.ceyloncab.eventmgtservice.domain.utils.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "vehicle-data",groupId = "${kafka.consumer.groupId}", containerFactory = "multiTypeKafkaListenerContainerFactory")
public class VehicleDataKafkaListener {

    @Autowired
    private EventService eventService;

    @KafkaHandler
    public void handleTripPriceForAllVehicle(TripPriceVehiclePublishResponse allVehiclePrice) {
        System.out.println("Trip Price received: " + allVehiclePrice);
        eventService.process(allVehiclePrice, EventType.VEHICLE_TRIP_PRICE);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        System.out.println("Unknown type received: " + object);
    }

}
