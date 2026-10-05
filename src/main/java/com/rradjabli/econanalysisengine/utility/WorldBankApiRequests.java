package com.rradjabli.econanalysisengine.utility;

import org.springframework.web.reactive.function.client.WebClient;

public class WorldBankApiRequests {

    private final static WebClient webClient = WebClient.builder().baseUrl("https://data360api.worldbank.org").build();

    public static String getRawNominalGdpData(){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data360/data")
                        .queryParam("DATABASE_ID", "WB_CLEAR")
                        .queryParam("INDICATOR", "WB_CLEAR_NY_GDP_MKTP_CD")
                        .queryParam("REF_AREA", "USA")
                        .queryParam("FREQ", "A")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public static String getRawRealGdpData(){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data360/data")
                        .queryParam("DATABASE_ID", "WB_WDI")
                        .queryParam("INDICATOR", "WB_WDI_NY_GDP_MKTP_KN")
                        .queryParam("REF_AREA", "USA")
                        .queryParam("FREQ", "A")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public static String getRawUnemploymentData(){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data360/data")
                        .queryParam("DATABASE_ID", "WB_GS")
                        .queryParam("INDICATOR", "WB_GS_SL_UEM_ZS")
                        .queryParam("REF_AREA", "USA")
                        .queryParam("FREQ", "A")
                        .queryParam("SEX", "M")
                        .queryParam("AGE", "Y15T24")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
