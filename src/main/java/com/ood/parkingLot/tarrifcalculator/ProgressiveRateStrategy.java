package com.ood.parkingLot.tarrifcalculator;

import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.model.Ticket;
import com.ood.parkingLot.service.ParkingSpotManager;

import java.math.BigDecimal;
import java.time.Duration;

public class ProgressiveRateStrategy implements TariffCalculatorStrategy{

    ParkingSpotManager parkingSpotManager;
    public ProgressiveRateStrategy(ParkingSpotManager parkingSpotManager) {
        this.parkingSpotManager = parkingSpotManager;
    }
    @Override
    public BigDecimal calculateTariff(Ticket ticket) {

        long minutesDifference = Duration.between(ticket.getParkedAt(), ticket.getUnparkedAt()).toMinutes();
        ParkingSpot parkingSpot = parkingSpotManager.getParkingSpotById(ticket.getParkingSpotId());

        if (minutesDifference <= 2) {
            return parkingSpot.getPrice().getAmount()
                    .multiply(BigDecimal.valueOf(minutesDifference));
        } else if (minutesDifference <= 5) {
            return BigDecimal.valueOf(10 + (minutesDifference - 2) * 7.0); // $7 per hour for the next 3 hours
        } else {
            return BigDecimal.valueOf(31 + (minutesDifference - 5) * 10.0); // $10 per hour for hours beyond 5
        }
    }
}
