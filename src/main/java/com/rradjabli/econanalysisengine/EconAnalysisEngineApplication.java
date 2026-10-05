package com.rradjabli.econanalysisengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EconAnalysisEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EconAnalysisEngineApplication.class, args);
    }

}
