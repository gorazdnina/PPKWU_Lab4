package com.example.Zad4.service;

import com.example.Zad4.client.CountClient;
import com.example.Zad4.dto.AnalysisRequest;
import com.example.Zad4.dto.CountRequest;
import com.example.Zad4.dto.CountResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class StringService {
    @Autowired
    CountClient client;
    public ResponseEntity<byte[]> countString(CountRequest countRequest) {
        CountResponse clientResponse = client.stringStatistics(countRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(countRequest.getFormat().equals("txt")) {
            httpHeaders.setContentType(MediaType.TEXT_PLAIN);
            return new ResponseEntity<>(txtFile(clientResponse), httpHeaders , HttpStatus.OK);
        } else if(countRequest.getFormat().equals("json")) {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(jsonFile(clientResponse), httpHeaders , HttpStatus.OK);
        } else if(countRequest.getFormat().equals("xml")) {
            httpHeaders.setContentType(MediaType.TEXT_XML);
            return new ResponseEntity<>(xmlFile(clientResponse), httpHeaders , HttpStatus.OK);
        } else if(countRequest.getFormat().equals("csv")) {
            httpHeaders.setContentType( new MediaType("text", "csv", Charset.forName("utf-8")) );
            return new ResponseEntity<>(csvFile(clientResponse), httpHeaders , HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    private byte[] txtFile(CountResponse clientResponse) {
        String str = """
        Uppercase letters: %d,
        Lowercase letters: %d,
        Digits: %d,
        Special signs: %d,
        Number of occurrences: %d
        """;
        return String.format(str, clientResponse.getUppercaseLetters(),clientResponse.getLowercaseLetters(),
                clientResponse.getDigits(), clientResponse.getSpecialSigns(), clientResponse.getNumOfOccurrences()).getBytes();

    }

    private byte[] jsonFile(CountResponse clientResponse) {
        String str = """ 
        {
        "Uppercase letters": %d,
        "Lowercase letters": %d,
        "Digits": %d,
        "Special signs": %d,
        "Number of occurrences": %d
        }
        """;
        return String.format(str, clientResponse.getUppercaseLetters(),clientResponse.getLowercaseLetters(),
                clientResponse.getDigits(), clientResponse.getSpecialSigns(), clientResponse.getNumOfOccurrences()).getBytes();

    }

    private byte[] xmlFile(CountResponse clientResponse) {
        String str = """
        <countResponse>
        <Uppercase_letters>%d</Uppercase_letters>,
        <Lowercase_letters>%d</Lowercase_letters>,
        <Digits>%d</Digits>,
        <Special_signs>%d</Special_signs>,
        <Number_of_occurrences>%d</Number_of_occurrences>
        </countResponse>
        """;
        return String.format(str, clientResponse.getUppercaseLetters(),clientResponse.getLowercaseLetters(),
                clientResponse.getDigits(), clientResponse.getSpecialSigns(), clientResponse.getNumOfOccurrences()).getBytes();

    }

    private byte[] csvFile(CountResponse clientResponse) {
        String str = """
        Uppercase_Letters,Lowercase_Letters, Digits, Special_Signs, Number_Of_Occurrences
        %d,%d,%d,%d,%d
        """;
        return String.format(str, clientResponse.getUppercaseLetters(),clientResponse.getLowercaseLetters(),
                clientResponse.getDigits(), clientResponse.getSpecialSigns(), clientResponse.getNumOfOccurrences()).getBytes();

    }
    public byte[] passedStringStatistics(AnalysisRequest analysisRequest) {
        return client.passedStringStatistics(analysisRequest);
    }

    public byte[] inputOutputFormat(String stringStatisticsFormat, String outputFormat, byte[] stringStatistics) throws IOException {
        if(stringStatisticsFormat.equals(outputFormat)) return stringStatistics;
        CountResponse cr = null;
        if(stringStatisticsFormat.equals("xml")){
            XmlMapper xmlMapper = new XmlMapper();
             cr = xmlMapper.readValue(stringStatistics, CountResponse.class);
        } else if(stringStatisticsFormat.equals("json")){
            cr = new ObjectMapper().readValue(stringStatistics,CountResponse.class);
        } else if(stringStatisticsFormat.equals("csv")){
            CsvSchema schema = CsvSchema.emptySchema().withHeader();
            CsvMapper csvMapper =new CsvMapper();
            cr = csvMapper.readerFor(CountResponse.class).with(schema).readValue(stringStatistics);
        } else if(stringStatisticsFormat.equals("txt")) {
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(new String(stringStatistics));
            List<Long> numbers = new ArrayList<>();
            while (m.find()){
                numbers.add(Long.parseLong(m.group()));
            }
            cr =  CountResponse.builder().uppercaseLetters(numbers.get(0)).lowercaseLetters(numbers.get(1)).digits(numbers.get(2))
            .specialSigns(numbers.get(3)).numOfOccurrences(numbers.get(4)).build();

        }


        return stringStatistics;
    }

}
