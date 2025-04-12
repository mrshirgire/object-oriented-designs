package com.ood.parkingLot.payment;

import com.ood.parkingLot.model.Account;

import java.math.BigDecimal;

public class UPIPayment implements PaymentStrategy{


    @Override
    public void pay(BigDecimal amount, Account account) {

        if(amount == null || amount.signum() < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }

        //TODO: third party integration to do payment;
    }
}
