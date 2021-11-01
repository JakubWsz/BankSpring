package com.kuba.bankspring.domain.balance;

import com.kuba.bankspring.entity.Balance;
import com.kuba.bankspring.entity.Client;
import com.kuba.bankspring.entity.CurrencyType;
import com.kuba.bankspring.infrastructure.repository.BalanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BalanceService {
    private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public Balance createBalance(CurrencyType currencyType, Client client){
       return balanceRepository.saveBalance(new Balance(BigDecimal.ZERO,currencyType,client));
    }
}

