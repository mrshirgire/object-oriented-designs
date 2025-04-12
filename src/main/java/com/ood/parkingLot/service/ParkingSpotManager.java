package com.ood.parkingLot.service;

import com.ood.parkingLot.enums.ParkingSpotType;
import com.ood.parkingLot.enums.VehicleType;
import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.model.Ticket;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public abstract class ParkingSpotManager {

    // sort parking sport based on available
    Map<UUID, ParkingSpot> parkingSpotsMap;
    Map<ParkingSpotType, Queue<ParkingSpot>> availableParkingSpots;
    Map<String, Ticket> ticketMap;

    public ParkingSpotManager(Map<UUID, ParkingSpot> parkingSpotsMap,  Map<ParkingSpotType, Queue<ParkingSpot>> availableParkingSpots) {
        this.parkingSpotsMap = parkingSpotsMap;
        this.availableParkingSpots = availableParkingSpots;
        ticketMap = new HashMap<>();
    }


    public abstract Queue<ParkingSpot> getParkingSpotsByType(ParkingSpotType parkingSpotType);
    public abstract ParkingSpot getParkingSpotById(UUID parkingSpotId);
    public abstract ParkingSpot getParkingSpotByVehicleId(String registeredVehicleNumber);
    public abstract UUID getAvailableParkingSpot(ParkingSpotType parkingSpotType);
    public abstract Ticket generateTicket(UUID parkingSpotId, String registeredVehicleId);
    public abstract Ticket getTicketByRegisteredVehicleNumber(String registeredVehicleNumber);
    public abstract void unparkVehicle(ParkingSpot parkingSpot);

    public void add(ParkingSpot parkingSpot) {
//        availableParkingSpots.add(parkingSpot);
//        parkingSpotsMap.put(parkingSpot.getParkingSpotId(), parkingSpot);
//        List<ParkingSpot> parkingSpotList = parkingSpotsTypeMap.get(parkingSpot.getParkingSpotType());
//        parkingSpotList.add(parkingSpot);
//        parkingSpotsTypeMap.put(parkingSpot.getParkingSpotType(), parkingSpotList);
    }
}
