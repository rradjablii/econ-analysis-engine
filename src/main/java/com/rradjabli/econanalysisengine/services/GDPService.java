package com.rradjabli.econanalysisengine.services;

import com.rradjabli.econanalysisengine.utility.EconomicDataParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class GDPService {

    private final WebClient webClient;
    private final EconomicDataParser economicDataParser;

    public GDPService(EconomicDataParser economicDataParser) {
        this.economicDataParser = economicDataParser;
        this.webClient = WebClient.builder()
                .baseUrl("https://data360api.worldbank.org")
                .build();
    }

    //U.S. gdp in constant US dollars.
    public ResponseEntity<String> getGDP_Real() {
        String response = webClient.get()
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

        Map<Integer, BigDecimal> gdpByYear = economicDataParser.parseValueByYear(response);

        return ResponseEntity.ok(gdpByYear.toString());
    }

    public ResponseEntity<String> getGDP_Nominal() {
        String response = webClient.get()
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

        Map<Integer, BigDecimal> gdpByYear = economicDataParser.parseValueByYear(response);

        return ResponseEntity.ok(gdpByYear.toString());
    }

}
