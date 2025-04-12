package com.ood.carrental.payment;

import com.ood.parkingLot.model.Account;

public interface PaymentStrategy {

    void pay(Price price, Account account);

}
