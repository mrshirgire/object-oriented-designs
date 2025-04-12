package com.ood.parkingLot.tarrifcalculator;

import com.ood.parkingLot.model.Ticket;
import com.ood.parkingLot.model.Vehicle;
import com.ood.parkingLot.service.ParkingSpotManager;

import java.math.BigDecimal;

public interface TariffCalculatorStrategy {

    BigDecimal calculateTariff(Ticket ticket);

}
