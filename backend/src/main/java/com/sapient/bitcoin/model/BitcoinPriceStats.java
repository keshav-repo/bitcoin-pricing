package com.sapient.bitcoin.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BitcoinPriceStats {
    private List<Stats> stats;
    private String currency;
    private Map<String, Double> history;
}
