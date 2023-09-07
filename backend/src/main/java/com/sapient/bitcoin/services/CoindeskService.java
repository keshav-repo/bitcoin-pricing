package com.sapient.bitcoin.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.bitcoin.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@Slf4j
public class CoindeskService implements BitCoinApiService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public HistoricalDataResponse pricesHistory(String start, String end, String curr) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(Constants.HISTORICAL_PATH)
                .queryParam("start", start)
                .queryParam("end", end)
                .queryParam("currency", curr);

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                return objectMapper.readValue(response.getBody(), HistoricalDataResponse.class);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Some error");
            }
        } else {
            throw new RuntimeException("Some error");
        }
    }
    @Override
    public List<CurrencyModel> currencySupported() {
        ResponseEntity<String> response = restTemplate.getForEntity(Constants.SUPPORTED_CURRENCY_PATH, String.class);
        if(response.getStatusCode().is2xxSuccessful()){
            try {
                return objectMapper.readValue(response.getBody(), List.class);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Some error");
            }
        }else {
            throw new RuntimeException("Some error");
        }
    }
}
