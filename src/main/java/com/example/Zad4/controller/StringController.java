package com.example.Zad4.controller;

import com.example.Zad4.client.CountClient;
import com.example.Zad4.dto.AnalysisFormatRequest;
import com.example.Zad4.dto.AnalysisRequest;
import com.example.Zad4.dto.CountRequest;
import com.example.Zad4.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StringController {
    @Autowired
    private StringService stringService;

    @PostMapping("/stringAnalysis")
    public ResponseEntity<byte[]> analysisString(@RequestBody AnalysisRequest analysisRequest) {

        byte[] statistics = stringService.passedStringStatistics(analysisRequest);
        return stringService.countString(analysisRequest);
    }
    @PostMapping("/analysisFormat")
    public ResponseEntity<byte[]> analysisFormat(@RequestBody AnalysisFormatRequest analysisFormatRequest) {
        return stringService.countString(analysisFormatRequest);
    }

}
