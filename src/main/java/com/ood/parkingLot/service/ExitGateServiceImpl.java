package com.ood.parkingLot.service;

import com.ood.parkingLot.model.Account;
import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.model.Ticket;
import com.ood.parkingLot.model.Vehicle;
import com.ood.parkingLot.payment.PaymentContext;
import com.ood.parkingLot.tarrifcalculator.TariffCalculator;

import java.math.BigDecimal;
import java.util.UUID;

public class ExitGateServiceImpl implements ExitGate{

    ParkingSpotManager parkingSpotManager;
    TariffCalculator tariffCalculator;
    PaymentContext paymentContext;
    public ExitGateServiceImpl(ParkingSpotManager parkingSpotManager, TariffCalculator tariffCalculator, PaymentContext paymentContext) {
        this.parkingSpotManager = parkingSpotManager;
        this.tariffCalculator = tariffCalculator;
        this.paymentContext = paymentContext;
    }

    @Override
    public Ticket getTicketByRegisteredVehicleNumber(String registeredVehicleNumber) {
        return parkingSpotManager.getTicketByRegisteredVehicleNumber(registeredVehicleNumber);
    }

    @Override
    public BigDecimal calculateTariff(Ticket ticket) {
        return tariffCalculator.calculateTariff(ticket);
    }

    @Override
    public void processPayment(BigDecimal amount, Account account) {
        paymentContext.processPayment(amount,  account);
    }


    @Override
    public void unparkVehicle(String registeredVehicleNumber)  {

        Ticket ticket = parkingSpotManager.getTicketByRegisteredVehicleNumber(registeredVehicleNumber);
        UUID parkingSpotId = ticket.getParkingSpotId();
        parkingSpotManager.unparkVehicle(parkingSpotManager.getParkingSpotById(parkingSpotId));
    }
}
