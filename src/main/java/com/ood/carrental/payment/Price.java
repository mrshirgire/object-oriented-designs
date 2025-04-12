package com.ood.carrental.payment;

import java.math.BigDecimal;
import java.util.Currency;

public class Price {

    BigDecimal price;
    Currency currency;
    public Price(BigDecimal price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }
}
