package com.ood.parkingLot.tarrifcalculator;

import com.ood.parkingLot.model.Ticket;

import java.math.BigDecimal;

public class TariffCalculator {

    TariffCalculatorStrategy tariffCalculatorStrategy;
    public TariffCalculator(TariffCalculatorStrategy tariffCalculatorStrategy) {
        this.tariffCalculatorStrategy = tariffCalculatorStrategy;
    }

    public BigDecimal calculateTariff(Ticket ticket) {
        return tariffCalculatorStrategy.calculateTariff(ticket);
    }
}
