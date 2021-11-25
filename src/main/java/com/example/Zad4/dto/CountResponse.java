package com.example.Zad4.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountResponse {
    private Long uppercaseLetters = 0L;
    private Long lowercaseLetters = 0L;
    private Long digits = 0L;
    private Long specialSigns = 0L;
    private Long numOfOccurrences = 0L;
}
