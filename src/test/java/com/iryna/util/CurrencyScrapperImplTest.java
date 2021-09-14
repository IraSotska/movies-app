package com.iryna.util;

import com.iryna.entity.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyScrapperImplTest {

    @Test
    void getExchangeRateTest() {
        CurrencyScrapperImpl currencyScrapper = new CurrencyScrapperImpl();
        currencyScrapper.getActualPrice(Currency.EUR);
    }

    @Test
    void getCurrencyPriceFromJsonTest() {
        String json = "[\n" +
                "{ \n" +
                "\"r030\":978,\"txt\":\"Євро\",\"rate\":31.5843,\"cc\":\"EUR\",\"exchangedate\":\"13.09.2021\"\n" +
                " }\n" +
                "]";
        CurrencyScrapperImpl currencyScrapper = new CurrencyScrapperImpl();
        assertEquals(31.5843D, currencyScrapper.getCurrencyPriceFromJsonResponse(json));
    }
}