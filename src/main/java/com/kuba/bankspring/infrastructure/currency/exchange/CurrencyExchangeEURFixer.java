package com.kuba.bankspring.infrastructure.currency.exchange;

import com.kuba.bankspring.domain.currency.exchange.CurrencyExchangeValueEUR;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CurrencyExchangeEURFixer implements CurrencyExchangeValueEUR {
    private final BigDecimal AUD;
    private final BigDecimal USD;
    private final BigDecimal CAD;
    private final BigDecimal PLN;

    RestTemplate restTemplate = new RestTemplate();

    public CurrencyExchangeEURFixer() {

        CurrencyExchangeModel currencyExchangeModel =
                restTemplate.getForObject(("http://data.fixer.io/api/latest?access_key=8f27de214f37cf179a4319f192faff31&symbols=USD,AUD,CAD,PLN"),
                        CurrencyExchangeModel.class);
        if (Objects.isNull(currencyExchangeModel)) {
            throw new RuntimeException("couldn't parse fields from request");
        }
        AUD = BigDecimal.valueOf(currencyExchangeModel.getRates().getAUD());
        USD = BigDecimal.valueOf(currencyExchangeModel.getRates().getUSD());
        CAD = BigDecimal.valueOf(currencyExchangeModel.getRates().getCAD());
        PLN = BigDecimal.valueOf(currencyExchangeModel.getRates().getPLN());
    }

    @Override
    public BigDecimal getAUD() {
        return AUD;
    }

    @Override
    public BigDecimal getUSD() {
        return USD;
    }

    @Override
    public BigDecimal getCAD() {
        return CAD;
    }

    @Override
    public BigDecimal getPLN() {
        return  PLN;
    }
}
