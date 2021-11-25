package com.example.Zad4.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisFormatRequest {
    private String inputFormat;
    private String outputFormat;
    private byte[] stringStatistics;

}
