package com.rradjabli.econanalysisengine.entitiy;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Data {

    public Data(Integer year, BigDecimal value) {
        this.year = year;
        this.value = value;
    }

    private Integer year;
    private BigDecimal value;

}
