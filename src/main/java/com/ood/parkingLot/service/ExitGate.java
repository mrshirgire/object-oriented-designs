package com.ood.parkingLot.service;

import com.ood.parkingLot.model.Account;
import com.ood.parkingLot.model.Ticket;
import com.ood.parkingLot.model.Vehicle;

import java.math.BigDecimal;

public interface ExitGate {

    Ticket getTicketByRegisteredVehicleNumber(String registeredVehicleNumber);
    BigDecimal calculateTariff(Ticket ticket);
     void processPayment(BigDecimal amount, Account vehicle);
     void unparkVehicle(String registeredVehicleNumber) throws InterruptedException;

}
