package com.ood.parkingLot.service;

import com.ood.parkingLot.enums.ParkingSpotType;
import com.ood.parkingLot.enums.VehicleType;
import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.model.Ticket;

import java.util.Queue;
import java.util.UUID;

public interface EntryGate {


    Queue<ParkingSpot> getParkingSpotsByParkingSpotType(ParkingSpotType parkingSpotType);
    UUID getAvailableParkingSpotId(ParkingSpotType parkingSpotType);
    Ticket generateTicket(String vehicleRegistrationNumber, UUID parkingSpotId);
}
