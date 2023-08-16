package com.ceyloncab.eventmgtservice.domain.entity.dto.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AcceptTripPublishResponse {
    private String status;
    private UserInfo userInfo;
    private AcceptTripData data;
    private ResHeaderPublish responseHeader;
}
