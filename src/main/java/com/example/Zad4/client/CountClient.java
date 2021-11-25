package com.example.Zad4.client;

import com.example.Zad4.dto.AnalysisRequest;
import com.example.Zad4.dto.CountRequest;
import com.example.Zad4.dto.CountResponse;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class CountClient {
    private RestTemplate restTemplate = new RestTemplate();

    public CountResponse stringStatistics(CountRequest statisticsRequest) {
        RequestEntity<CountRequest> request = RequestEntity
                .post("http://localhost:8090/request").body(statisticsRequest);

        return restTemplate.exchange(request, CountResponse.class).getBody();
    }

    public byte[] passedStringStatistics(AnalysisRequest analysisRequest) {
        Map<String, String> requestBodyMap = new HashMap<>();
        requestBodyMap.put("string", analysisRequest.getString());
        requestBodyMap.put("expression", analysisRequest.getExpression());
        requestBodyMap.put("format", analysisRequest.getFirstFormat());
        RequestEntity<Map<String,String>> request = RequestEntity.post("http://localhost:8090/request").body(requestBodyMap);
        return restTemplate.exchange(request, byte[].class).getBody();
    }
}
