package com.konectape.diviswap.api;

import com.konectape.diviswap.models.CoinExchange;
import com.konectape.diviswap.utils.Utils;

public class Api {
    public CoinExchange getCoinsCode() {
        try {
            return Utils.request();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }
}
