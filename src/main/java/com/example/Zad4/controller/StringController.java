package com.example.Zad4.controller;

import com.example.Zad4.dto.AnalysisFormatRequest;
import com.example.Zad4.dto.AnalysisRequest;
import com.example.Zad4.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class StringController {
    @Autowired
    private StringService stringService;

    @PostMapping("/stringAnalysis")
    public ResponseEntity<byte[]> analysisString(@RequestBody AnalysisRequest analysisRequest) throws IOException {

        byte[] statistics = stringService.passedStringStatistics(analysisRequest);
        return ResponseEntity.ok(stringService.inputOutputFormat(analysisRequest.getFirstFormat(), analysisRequest.getOutputFormat(), statistics));
    }
    @PostMapping("/analysisFormat")
    public ResponseEntity<byte[]> analysisFormat(@RequestBody AnalysisFormatRequest analysisFormatRequest) throws IOException {

        return ResponseEntity.ok(stringService.inputOutputFormat(analysisFormatRequest.getInputFormat(), analysisFormatRequest.getInputFormat(), analysisFormatRequest.getStringStatistics()));
    }

}
