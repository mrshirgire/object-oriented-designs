package com.ood.parkingLot.service;

import com.ood.parkingLot.model.Ticket;

import java.math.BigDecimal;

public interface TicketService {

    Ticket createTicket(String parkingSpotId, String registeredVehicleId);


}
