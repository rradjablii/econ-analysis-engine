package com.rradjabli.econanalysisengine.controllers;

import com.rradjabli.econanalysisengine.services.GDPService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/data/gdp")
public class GDPController {

    public GDPController(GDPService GDPService){
        this.GDPService = GDPService;
    }

    private final GDPService GDPService;

    @GetMapping("/real")
    public ResponseEntity<String> getGdp_real() {
        return GDPService.getGDP_Real();
    }

    @GetMapping("/nominal")
    public ResponseEntity<String> getGdp_nominal() {
        return GDPService.getGDP_Nominal();
    }

    @GetMapping("/divergence")
    public ResponseEntity<String> getGdp_divergence() {
        return GDPService.getGDP_Divergence();
    }

}
