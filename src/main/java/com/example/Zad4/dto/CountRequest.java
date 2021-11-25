package com.example.Zad4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountRequest {
    private String string;
    private String expression;
    private String format;
}
