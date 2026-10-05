package com.rradjabli.econanalysisengine.entitiy;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonPropertyOrder({
        "indicator",
        "country",
        "economicData"
})
public class EconomicDataTimeSeries {

    private String indicator;
    private String country;
    private Map<Integer, BigDecimal> economicData = new HashMap<>();
}
