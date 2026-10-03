package com.rradjabli.econanalysisengine.services;

import com.rradjabli.econanalysisengine.utility.DataCalculator;
import com.rradjabli.econanalysisengine.utility.EconomicDataParser;
import com.rradjabli.econanalysisengine.utility.FredApiRequests;
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
    public ResponseEntity<String> getGDP_Real() {
        String response = FredApiRequests.getRawRealGdpData();

        Map<Integer, BigDecimal> gdpByYear = economicDataParser.parseValueByYear(response);

        return ResponseEntity.ok(gdpByYear.toString());
    }

    //U.S. gdp in current US dollars.
    public ResponseEntity<String> getGDP_Nominal() {
        String response = FredApiRequests.getRawNominalGdpData();

        Map<Integer, BigDecimal> gdpByYear = economicDataParser.parseValueByYear(response);

        return ResponseEntity.ok(gdpByYear.toString());
    }

    //divergence between real and nominal gdp.
    public ResponseEntity<String> getGDP_Divergence() {
        String real = FredApiRequests.getRawRealGdpData();
        String nominal = FredApiRequests.getRawNominalGdpData();
        Map<Integer, BigDecimal> divergence = DataCalculator.calculateCumulativeDivergence(
                economicDataParser.parseValueByYear(real),
                economicDataParser.parseValueByYear(nominal));
        return ResponseEntity.ok(divergence.toString());
    }



}
