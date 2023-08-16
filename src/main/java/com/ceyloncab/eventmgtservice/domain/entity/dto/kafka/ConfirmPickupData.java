package com.ceyloncab.eventmgtservice.domain.entity.dto.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmPickupData extends ConfirmPickup{
    private String customerName;
    private String mobileNumber;
    private String distance;
    private String estimatedTime;
    private Double price;
}
