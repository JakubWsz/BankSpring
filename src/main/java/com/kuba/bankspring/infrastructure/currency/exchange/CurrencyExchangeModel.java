package com.kuba.bankspring.infrastructure.currency.exchange;

public class CurrencyExchangeModel {
    private boolean success;
    private Integer timestamp;
    private String base;
    private String date;
    private Rates rates;

    public boolean isSuccess() {
        return success;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Rates getRates() {
        return rates;
    }

    @Override
    public String toString() {
        return "CurrencyExchangeModel{" +
                "success=" + success +
                ", timestamp=" + timestamp +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }
}
