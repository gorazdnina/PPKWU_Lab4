package com.example.Zad4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisRequest {
    private String firstFormat;
    private String outputFormat;
    private String string;
    private String expression;

}
