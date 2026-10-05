package com.rradjabli.econanalysisengine.services;

import com.rradjabli.econanalysisengine.utility.DataCalculator;
import com.rradjabli.econanalysisengine.utility.EconomicDataParser;
import com.rradjabli.econanalysisengine.utility.WorldBankApiRequests;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class GDPService {

    private final EconomicDataParser economicDataParser;

    public GDPService(EconomicDataParser economicDataParser) {
        this.economicDataParser = economicDataParser;
    }

    //U.S. gdp in constant US dollars.
    @Cacheable(value = "economicData", key = "'realGdp'")
    public ResponseEntity<String> getGDP_Real() {
        String response = WorldBankApiRequests.getRawRealGdpData();

        Map<Integer, BigDecimal> gdpByYear = economicDataParser.parseValueByYear(response);

        return ResponseEntity.ok(gdpByYear.toString());
    }

    //U.S. gdp in current US dollars.
    @Cacheable(value = "economicData", key = "'nominalGdp'")
    public ResponseEntity<String> getGDP_Nominal() {
        String response = WorldBankApiRequests.getRawNominalGdpData();

        Map<Integer, BigDecimal> gdpByYear = economicDataParser.parseValueByYear(response);

        return ResponseEntity.ok(gdpByYear.toString());
    }

    //divergence between real and nominal gdp.
    @Cacheable(value = "economicData", key = "'divergence'")
    public ResponseEntity<String> getGDP_Divergence() {
        String real = WorldBankApiRequests.getRawRealGdpData();
        String nominal = WorldBankApiRequests.getRawNominalGdpData();
        Map<Integer, BigDecimal> divergence = DataCalculator.calculateCumulativeDivergence(
                economicDataParser.parseValueByYear(real),
                economicDataParser.parseValueByYear(nominal));
        return ResponseEntity.ok(divergence.toString());
    }

    @Cacheable(value = "economicData", key = "'unemploymentRate'")
    public ResponseEntity<String> getUnemploymentRate() {
        String unemploymentRate = WorldBankApiRequests.getRawUnemploymentData();
        unemploymentRate = economicDataParser.parseValueByYear(unemploymentRate).toString();
        return ResponseEntity.ok(unemploymentRate);
    }



}
