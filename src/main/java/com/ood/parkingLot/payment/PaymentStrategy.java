package com.ood.parkingLot.payment;

import com.ood.parkingLot.model.Account;

import java.math.BigDecimal;

public interface PaymentStrategy {

    void pay(BigDecimal amount, Account account);
}
