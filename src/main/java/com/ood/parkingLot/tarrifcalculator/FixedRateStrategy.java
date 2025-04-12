package com.ood.parkingLot.tarrifcalculator;

import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.model.Ticket;
import com.ood.parkingLot.service.ParkingSpotManager;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class FixedRateStrategy implements TariffCalculatorStrategy{

    ParkingSpotManager parkingSpotManager;
    public FixedRateStrategy(ParkingSpotManager parkingSpotManager) {
        this.parkingSpotManager = parkingSpotManager;
    }

    @Override
    public BigDecimal calculateTariff(Ticket ticket) {

        long minutesDifference = Duration.between(ticket.getParkedAt(), LocalDateTime.now()).toMinutes();

        UUID parkingSpotId = ticket.getParkingSpotId();
        ParkingSpot parkingSpot = parkingSpotManager.getParkingSpotById(parkingSpotId);

        return parkingSpot.getPrice().getAmount()
                .multiply(BigDecimal.valueOf(minutesDifference));
    }
}
