package com.currency.converter.controller;

import com.currency.converter.model.CurrencyConversionRequest;
import com.currency.converter.model.CurrencyConversionResponse;
import com.currency.converter.service.CurrencyConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @GetMapping("/rates")
    public ResponseEntity<Object> getExchangeRates(@RequestParam(value = "base", defaultValue = "USD") String base) {
        try {
            return ResponseEntity.ok(currencyConverterService.getExchangeRates(base));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching exchange rates: " + e.getMessage());
        }
    }

    @PostMapping("/convert")
    public ResponseEntity<Object> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        try {
            double convertedAmount = currencyConverterService.convertCurrency(request.getFrom(), request.getTo(), request.getAmount());
            return ResponseEntity.ok(new CurrencyConversionResponse(request.getFrom(), request.getTo(), request.getAmount(), convertedAmount));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Invalid currency codes or amount.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error performing currency conversion: " + e.getMessage());
        }
    }
}
