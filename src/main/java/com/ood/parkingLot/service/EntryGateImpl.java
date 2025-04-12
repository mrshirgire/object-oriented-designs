package com.ood.parkingLot.service;

import com.ood.parkingLot.enums.ParkingSpotType;
import com.ood.parkingLot.enums.TicketStatus;
import com.ood.parkingLot.enums.VehicleType;
import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.model.Ticket;

import java.time.LocalDateTime;
import java.util.Queue;
import java.util.UUID;

public class EntryGateImpl implements EntryGate {

    ParkingSpotManager parkingSpotManager;
    public EntryGateImpl(ParkingSpotManager parkingSpotManager) {
        this.parkingSpotManager = parkingSpotManager;
    }

    @Override
    public Queue<ParkingSpot> getParkingSpotsByParkingSpotType(ParkingSpotType parkingSpotType) {

        if(parkingSpotType == null){
            throw new IllegalArgumentException("parkingSpotType cannot be null");
        }

        return parkingSpotManager.getParkingSpotsByType(parkingSpotType);
    }

    @Override
    public UUID getAvailableParkingSpotId(ParkingSpotType parkingSpotType) {

        if(parkingSpotType == null){
            throw new IllegalArgumentException("parkingSpotType cannot be null");
        }

        return parkingSpotManager.getAvailableParkingSpot(parkingSpotType);
    }

    @Override
    public Ticket generateTicket(String vehicleRegistrationNumber, UUID parkingSpotId) {
        return parkingSpotManager.generateTicket(parkingSpotId, vehicleRegistrationNumber);
    }
}
