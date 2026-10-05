package com.rradjabli.econanalysisengine.controllers;

import com.rradjabli.econanalysisengine.entitiy.Data;
import com.rradjabli.econanalysisengine.services.DataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/data/gdp")
public class DataController {

    public DataController(DataService DataService){
        this.DataService = DataService;
    }

    private final DataService DataService;

    @GetMapping("/real")
    public ResponseEntity<String> getGdp_real() {
        return DataService.getGDP_Real();
    }

    @GetMapping("/nominal")
    public ResponseEntity<String> getGdp_nominal() {
        return DataService.getGDP_Nominal();
    }

    @GetMapping("/divergence")
    public ResponseEntity<String> getGdp_divergence() {
        return DataService.getGDP_Divergence();
    }

    @GetMapping("/unemployment")
    public ResponseEntity<List<Data>> getUnemploymentRate() {
        return DataService.getUnemploymentRate();
    }

}
