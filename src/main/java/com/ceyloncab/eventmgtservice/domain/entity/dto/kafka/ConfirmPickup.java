package com.ceyloncab.eventmgtservice.domain.entity.dto.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfirmPickup {
    private String userId;
    private String channel;
    private String uuid;
    private String tripId;
    private Location start;
    private Location destination;
    private List<Location> midStops;
    private String vehicleType;
    private String note;
    private String promoCode;
    private String secondaryNumber;
    private String paymentType;
}
