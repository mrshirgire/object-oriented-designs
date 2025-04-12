package com.ood.parkingLot.payment;

import com.ood.parkingLot.model.Account;

import java.math.BigDecimal;

public class PaymentContext {

    PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(BigDecimal amount, Account account) {

        if(paymentStrategy == null || amount == null || account == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        paymentStrategy.pay(amount, account);
    }
}
