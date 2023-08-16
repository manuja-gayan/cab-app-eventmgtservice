package com.ceyloncab.eventmgtservice.domain.entity.dto.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonPublish<T> {
    private String status;
    private UserInfo userInfo;
    private T data;
    private ResHeaderPublish responseHeader;

    @Override
    public String toString() {
        return "CommonPublish{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", responseHeader=" + responseHeader +
                '}';
    }
}
