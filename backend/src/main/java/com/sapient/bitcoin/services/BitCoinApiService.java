package com.sapient.bitcoin.services;

import com.sapient.bitcoin.model.CurrencyModel;
import com.sapient.bitcoin.model.HistoricalDataResponse;

import java.util.List;

public interface BitCoinApiService {
    public HistoricalDataResponse pricesHistory(String start, String end, String curr);

    public List<CurrencyModel> currencySupported();
}
