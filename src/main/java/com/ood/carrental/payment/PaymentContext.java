package com.ood.carrental.payment;

import com.ood.parkingLot.model.Account;

import java.math.BigDecimal;

public class PaymentContext {

    PaymentStrategy paymentStrategy;
    PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(Price price, Account account) {
        paymentStrategy.pay(price, account);
    }
}
