package com.currency.converter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class CurrencyConverterService {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/694f1a9af347b4f4ef15ac97/latest/";

    private final RestTemplate restTemplate;

    public CurrencyConverterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getExchangeRates(String baseCurrency) {
        String url = API_URL + baseCurrency;
        return restTemplate.getForObject(url, Map.class);
    }

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        Map<String, Object> rates = getExchangeRates(fromCurrency);
        if (rates != null && rates.containsKey("conversion_rates")) {
            Map<String, Double> conversionRates = (Map<String, Double>) rates.get("conversion_rates");
            if (conversionRates.containsKey(toCurrency)) {
                double rate = conversionRates.get(toCurrency);
                return amount * rate;
            }
        }
        throw new IllegalArgumentException("Invalid currency codes or unable to fetch exchange rates");
    }
}
