package com.kuba.bankspring.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class TransferAmount extends BaseEntity{
    private BigDecimal amount;
    private CurrencyType currencyType;


    public TransferAmount(BigDecimal amount, CurrencyType currencyType) {
        this.amount = amount;
        this.currencyType = currencyType;
    }

    public TransferAmount() {}

    public BigDecimal getAmount() {
        return amount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

}
