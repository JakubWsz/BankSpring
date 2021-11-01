package com.kuba.bankspring.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Balance extends BaseEntity{
    private BigDecimal amount;
    private CurrencyType currencyType;


    public Balance(BigDecimal amount, CurrencyType currencyType) {
        this.amount = amount;
        this.currencyType = currencyType;
    }

    public Balance() {

    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "amount=" + amount +
                ", currencyType=" + currencyType +
                '}';
    }
}
