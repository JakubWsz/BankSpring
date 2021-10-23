package com.kuba.bankspring.domain.currency.exchange;

import com.kuba.bankspring.entity.CurrencyType;
import com.kuba.bankspring.entity.TransferAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyExchangeServiceTest {
    private static final double EUR_TO_PLN = 4.61;
    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;
    @Mock
    private CurrencyExchangeValueEUR currencyExchangeValueEUR;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void currencyExchangeWithoutProvision() {
        //given
        TransferAmount primaryTransferAmount = new TransferAmount(BigDecimal.valueOf(521.05), CurrencyType.PLN);
        CurrencyType toExchange = CurrencyType.EUR;
        Mockito.when(currencyExchangeValueEUR.getPLN()).thenReturn(BigDecimal.valueOf(EUR_TO_PLN));

        //when
        BigDecimal amountEUR =
                currencyExchangeService.currencyExchangeWithoutProvision(primaryTransferAmount, toExchange);

        //then
        System.out.println(amountEUR + " amountEUR");
        System.out.println(primaryTransferAmount.getAmount()
                .divide(BigDecimal.valueOf(EUR_TO_PLN),RoundingMode.HALF_EVEN));
        assertEquals(0, BigDecimal.valueOf(113.14).compareTo(amountEUR));
    }
}