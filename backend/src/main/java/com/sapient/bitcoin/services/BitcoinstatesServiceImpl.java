package com.sapient.bitcoin.services;

import com.sapient.bitcoin.model.BitcoinPriceStats;
import com.sapient.bitcoin.model.HistoricalDataResponse;
import com.sapient.bitcoin.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BitcoinstatesServiceImpl implements BitcoinstatesService {
    @Autowired
    private BitCoinApiService bitCoinApiService;

    @Override
    public BitcoinPriceStats getStates(String start, String end, String curr) {
        curr = !StringUtils.hasText(curr) ? "USD" : curr;
        HistoricalDataResponse historicalDataResponse = bitCoinApiService.pricesHistory(start, end, curr);
        Stats highestPriceStats = new Stats("Highest price", Double.MIN_VALUE);
        Stats lowestPriceStats = new Stats("Lowest price", Double.MAX_VALUE);
        if (!Objects.isNull(historicalDataResponse.getBpi())) {
            for (Map.Entry<String, Double> datePrice : historicalDataResponse.getBpi().entrySet()) {
                if (highestPriceStats.getValue() < datePrice.getValue()) {
                    highestPriceStats.setValue(datePrice.getValue());
                    highestPriceStats.setDate(datePrice.getKey());
                }
                if (lowestPriceStats.getValue() > datePrice.getValue()) {
                    lowestPriceStats.setValue(datePrice.getValue());
                    lowestPriceStats.setDate(datePrice.getKey());
                }
            }
            return BitcoinPriceStats.builder()
                    .stats(List.of(lowestPriceStats, highestPriceStats))
                    .currency(curr)
                    .history(historicalDataResponse.getBpi())
                    .build();
        }else {
            return BitcoinPriceStats.builder()
                    .currency(curr)
                    .build();
        }

    }
}
