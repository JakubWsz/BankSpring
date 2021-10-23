package com.kuba.bankspring.domain.currency.exchange;



import com.kuba.bankspring.entity.CurrencyType;
import com.kuba.bankspring.entity.TransferAmount;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyExchangeService {
    private static final double PERCENTAGE_PROVISION = 0.042;
    private final CurrencyExchangeValueEUR currencyExchangeValueEUR;

    public CurrencyExchangeService(CurrencyExchangeValueEUR currencyExchangeValueEUR) {
        this.currencyExchangeValueEUR = currencyExchangeValueEUR;
    }

    public BigDecimal currencyExchangeWithoutProvision(TransferAmount primaryTransferAmount, CurrencyType toExchange) {
        return currencyExchange(primaryTransferAmount, toExchange);
    }

    public BigDecimal currencyExchangeWithProvision(TransferAmount primaryTransferAmount, CurrencyType toExchange) {
        BigDecimal provisionAmount = currencyExchangeWithoutProvision(primaryTransferAmount, toExchange)
                .multiply(BigDecimal.valueOf(PERCENTAGE_PROVISION));
        return currencyExchangeWithoutProvision(primaryTransferAmount, toExchange)
                .subtract(provisionAmount);
    }

    private BigDecimal currencyExchange(TransferAmount primaryTransferAmount, CurrencyType toExchange) {
        BigDecimal rate = BigDecimal.ZERO;
        switch (primaryTransferAmount.getCurrencyType()) {
            case USD:
                rate = currencyExchangeValueEUR.getUSD();
                break;
            case AUD:
                rate = currencyExchangeValueEUR.getAUD();
                break;
            case CAD:
                rate = currencyExchangeValueEUR.getCAD();
                break;
            case PLN:
                rate = currencyExchangeValueEUR.getPLN();
                break;
            case EUR:
                rate = BigDecimal.ONE;
                break;
        }
        BigDecimal rateToExchange = BigDecimal.ZERO;
        switch (toExchange) {
            case USD:
                rateToExchange = currencyExchangeValueEUR.getUSD();
                break;
            case AUD:
                rateToExchange = currencyExchangeValueEUR.getAUD();
                break;
            case CAD:
                rateToExchange = currencyExchangeValueEUR.getCAD();
                break;
            case PLN:
                rateToExchange = currencyExchangeValueEUR.getPLN();
                break;
            case EUR:
                rateToExchange = BigDecimal.ONE;
                break;
        }
        return primaryTransferAmount.getAmount().multiply(rateToExchange)
                .divide(rate,RoundingMode.HALF_EVEN);
    }
}
