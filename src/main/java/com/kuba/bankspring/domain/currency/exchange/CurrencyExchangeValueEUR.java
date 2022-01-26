package com.kuba.bankspring.domain.currency.exchange;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;


public interface CurrencyExchangeValueEUR {
    BigDecimal getAUD();
    BigDecimal getUSD();
    BigDecimal getCAD();
    BigDecimal getPLN();
}
