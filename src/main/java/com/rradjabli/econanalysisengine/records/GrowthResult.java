package com.rradjabli.econanalysisengine.records;

import java.math.BigDecimal;
import java.util.Map;

public record GrowthResult(
        Map<Integer, BigDecimal> real,
        Map<Integer, BigDecimal> nominal) {
}
