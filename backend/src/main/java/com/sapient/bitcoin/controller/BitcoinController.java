package com.sapient.bitcoin.controller;

import com.sapient.bitcoin.model.BitcoinPriceStats;
import com.sapient.bitcoin.model.CurrencyModel;
import com.sapient.bitcoin.services.BitCoinApiService;
import com.sapient.bitcoin.services.BitcoinstatesService;
import com.sapient.bitcoin.services.CoindeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bitcoin")
public class BitcoinController {

    @Autowired
    private BitcoinstatesService bitcoinstatesService;

    @Autowired
    private BitCoinApiService bitCoinApiService;

    @GetMapping("/history")
    public BitcoinPriceStats fetchStates(@RequestParam(required = true) String startDate,
                                         @RequestParam(required = true) String endDate,
                                         @RequestParam(required = false) String currency
    ) {
        return bitcoinstatesService.getStates(startDate, endDate, currency);
    }

    @GetMapping("/currency")
    public List<CurrencyModel> currencySupported(){
        return bitCoinApiService.currencySupported();
    }
}
