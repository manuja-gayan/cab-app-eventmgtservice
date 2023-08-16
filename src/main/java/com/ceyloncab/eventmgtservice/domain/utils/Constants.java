package com.ceyloncab.eventmgtservice.domain.utils;

import lombok.Getter;

public class Constants {
    public static final String UNHANDLED_ERROR_CODE = "EMS3000";
    @Getter
    public enum ResponseData {
        COMMON_SUCCESS("EMS1000", "Success","200"),
        QUERY_SUCCESS("EMS1001", "Verified","200"),
        COMMON_FAIL("EMS2000", "Failed","400"),
        INTERNAL_SERVER_ERROR("EMS3001", "Internal Server Error","500");

        private String code;
        private String message;
        private String responseCode;

        ResponseData(String code, String message, String responseCode) {
            this.code = code;
            this.message = message;
            this.responseCode= responseCode;
        }
    }
}
