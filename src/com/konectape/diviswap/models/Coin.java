package com.konectape.diviswap.models;

import java.util.List;

public class Coin {
    private Object conversionRates;

    public Coin(Object conversionRates) {
        this.conversionRates = conversionRates;
    }

    public Coin(CoinExchange coinExchange) {
        this.conversionRates = coinExchange.conversion_rates();
    }

    public Object getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Object conversionRates) {
        this.conversionRates = conversionRates;
    }
}
