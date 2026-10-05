package com.rradjabli.econanalysisengine.utility;

import com.rradjabli.econanalysisengine.entitiy.Data;
import org.springframework.stereotype.Component;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.*;

@Component
public class EconomicDataParser {

    private final ObjectMapper objectMapper;

    public EconomicDataParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Map<Integer, BigDecimal> parseValueByYear(String json){

        Map<Integer, BigDecimal> gdpByYear = new HashMap<>();

        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode array = findArrayInsideNode(root);

            assert array != null;
            for (JsonNode observation : array) {
                JsonNode yearNode = observation.get("TIME_PERIOD");
                JsonNode valueNode = observation.get("OBS_VALUE");

                if (yearNode == null || valueNode == null || valueNode.isNull()) {
                    continue;
                }

                int year = yearNode.asInt();
                BigDecimal value = new BigDecimal(valueNode.asString());

                gdpByYear.put(year, value);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse economic data", e);
        }

        return gdpByYear;
    }

    private JsonNode findArrayInsideNode(JsonNode node) {

        if (node.isNull()) {
            return null;
        }

        if (node.isArray()) {
            return node;
        }

        if (node.isObject()) {

            for (JsonNode child : node) {

                JsonNode result = findArrayInsideNode(child);

                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public List<Data> parseDataToObjectList(String json){
        List<Data> data = new ArrayList<>();

        try{
            JsonNode root = objectMapper.readTree(json);
            JsonNode array = findArrayInsideNode(root);

            assert array != null;
            for (JsonNode observation : array) {
                JsonNode yearNode = observation.get("TIME_PERIOD");
                JsonNode valueNode = observation.get("OBS_VALUE");

                if(yearNode == null || valueNode == null || valueNode.isNull()){
                    continue;
                }

                data.add(new Data(yearNode.asInt(), new BigDecimal(valueNode.asString())));

            }
        }catch (Exception e){
            throw new RuntimeException("Failed to parse economic data to list of objects", e);
        }

        return data;
    }

}


