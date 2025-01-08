# Currency Converter API Documentation

This API provides currency conversion functionality and access to real-time exchange rates.

## Base URL
```
http://localhost:8080/api
```

## Endpoints

### 1. Convert Currency
Convert an amount from one currency to another.

**Endpoint:** `/convert`  
**Method:** POST

#### Request Format
```json
{
    "from": "USD",
    "to": "INR",
    "amount": 100
}
```

#### Response Format
```json
{
    "from": "USD",
    "to": "INR",
    "amount": 100.0,
    "convertedAmount": 8583.240000000002
}
```

### 2. Get Exchange Rates
Retrieve current exchange rates for all supported currencies against a base currency.

**Endpoint:** `/rates`  
**Method:** GET

#### Query Parameters
- `base`: Base currency code (e.g., USD)

#### Response Format
```json
{
    "result": "success",
    "documentation": "https://www.exchangerate-api.com/docs",
    "terms_of_use": "https://www.exchangerate-api.com/terms",
    "time_last_update_utc": "Wed, 08 Jan 2025 00:00:01 +0000",
    "time_next_update_utc": "Thu, 09 Jan 2025 00:00:01 +0000",
    "base_code": "USD",
    "conversion_rates": {
        "USD": 1,
        "EUR": 0.9648,
        "GBP": 0.8001,
        // ... (rates for all supported currencies)
    }
}
```

## Supported Currencies
The API supports a wide range of currencies including but not limited to:
- USD (US Dollar)
- EUR (Euro)
- GBP (British Pound)
- JPY (Japanese Yen)
- INR (Indian Rupee)
- And many more...

## Rate Updates
- Exchange rates are updated daily at 00:00:01 UTC
- The response includes timestamps for the last update and next scheduled update

## Data Source
Exchange rate data is provided by Exchange Rate API. For more information, visit:
- Documentation: https://www.exchangerate-api.com/docs
- Terms of Use: https://www.exchangerate-api.com/terms
