package com.kuba.bankspring.infrastructure.currency.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {
    private double USD;
    private double AUD;
    private double CAD;
    private double PLN;

    public Rates() {
    }

    public Rates(double USD, double AUD, double CAD, double PLN) {
        this.USD = USD;
        this.AUD = AUD;
        this.CAD = CAD;
        this.PLN = PLN;
    }

    @JsonProperty("USD")
    public double getUSD() {
        return USD;
    }

    @JsonProperty("AUD")
    public double getAUD() {
        return AUD;
    }

    @JsonProperty("CAD")
    public double getCAD() {
        return CAD;
    }

    @JsonProperty("PLN")
    public double getPLN() {
        return PLN;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    public void setAUD(double AUD) {
        this.AUD = AUD;
    }

    public void setCAD(double CAD) {
        this.CAD = CAD;
    }

    public void setPLN(double PLN) {
        this.PLN = PLN;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "usd=" + USD +
                ", aud=" + AUD +
                ", cad=" + CAD +
                ", pln=" + PLN +
                '}';
    }
}
