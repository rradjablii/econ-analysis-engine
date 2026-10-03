package com.rradjabli.econanalysisengine.utility;

import com.rradjabli.econanalysisengine.records.GrowthResult;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataCalculator {

    public static Map<Integer, BigDecimal> calculateCumulativeDivergence(Map<Integer, BigDecimal> real,
                                                                         Map<Integer, BigDecimal> nominal){
        Map<Integer, BigDecimal> divergence = new HashMap<>();

        GrowthResult growthResult = calculateGrowthFromBase(real, nominal);

        Map<Integer, BigDecimal> realCalculated = growthResult.real();
        Map<Integer, BigDecimal> nominalCalculated = growthResult.nominal();

        for(int year:realCalculated.keySet()){
            BigDecimal realValue = realCalculated.get(year);
            BigDecimal nominalValue = nominalCalculated.get(year);
            divergence.put(year, nominalValue.subtract(realValue));
        }
        return divergence;
    }

    public static GrowthResult calculateGrowthFromBase(Map<Integer, BigDecimal> real,
                                                   Map<Integer, BigDecimal> nominal){

        //Get years that are present in both maps.
        List<Integer> years = getYears(real, nominal);

        Map<Integer, BigDecimal> realGrowth = calculateGrowthIndividually(real, years);
        Map<Integer, BigDecimal> nominalGrowth = calculateGrowthIndividually(nominal, years);

        return new GrowthResult(realGrowth, nominalGrowth);
    }

    private static Map<Integer, BigDecimal> calculateGrowthIndividually(Map<Integer, BigDecimal> valueSet,
                                                                        List<Integer> years){
        BigDecimal baseValue = valueSet.get(years.getFirst());

        Map<Integer, BigDecimal> growth = new HashMap<>();

        for(int year:years){
            BigDecimal value = valueSet.get(year);
            BigDecimal calculatedGrowth = value.divide(baseValue, 5, RoundingMode.HALF_UP).subtract(BigDecimal.ONE);
            growth.put(year, calculatedGrowth);
        }

        return growth;
    }

    private static List<Integer> getYears(Map<Integer, BigDecimal> real, Map<Integer, BigDecimal> nominal){
        return real.keySet()
                .stream()
                .filter(nominal::containsKey)
                .sorted()
                .toList();
    }
}
