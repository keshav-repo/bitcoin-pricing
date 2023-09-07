package com.sapient.bitcoin.services;

import com.sapient.bitcoin.model.BitcoinPriceStats;
import com.sapient.bitcoin.model.CurrencyModel;

import java.util.List;

public interface BitcoinstatesService {
    public BitcoinPriceStats getStates(String start, String end, String curr);

}
