package com.ood.parkingLot.service;

import com.ood.parkingLot.enums.ParkingSpotType;
import com.ood.parkingLot.enums.TicketStatus;
import com.ood.parkingLot.enums.VehicleType;
import com.ood.parkingLot.exception.ParkingSpotNotFoundException;
import com.ood.parkingLot.exception.TicketNotFoundException;
import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.model.Ticket;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarSpotManager extends ParkingSpotManager {

    private final Lock lock = new ReentrantLock();

    public CarSpotManager( Map<UUID, ParkingSpot> parkingSpotsMap, Map<ParkingSpotType, Queue<ParkingSpot>> availableParkingSpots) {
        super(parkingSpotsMap, availableParkingSpots);
    }

    @Override
    public Queue<ParkingSpot> getParkingSpotsByType(ParkingSpotType parkingSpotType) {

        if (parkingSpotType == null) {
            throw new IllegalArgumentException("parkingSpotType cannot be null");
        }

        Queue<ParkingSpot> parkingSpots = availableParkingSpots.get(parkingSpotType);
        if(parkingSpots == null || parkingSpots.isEmpty()) {
            throw new ParkingSpotNotFoundException("No parking spots found for type " + parkingSpotType, new NullPointerException());
        }

        return parkingSpots;
        //return availableParkingSpots.get(parkingSpotType);
    }

    @Override
    public ParkingSpot getParkingSpotById(UUID parkingSpotId) {
        if (parkingSpotId == null) {
            throw new IllegalArgumentException("parkingSpotId cannot be null");
        }

        if(parkingSpotsMap == null || parkingSpotsMap.get(parkingSpotId) == null) {
            //throw new ParkingSpotNotFoundException()
            throw new ParkingSpotNotFoundException("ParkingSpot with id " + parkingSpotId + " not found");
        }


        return parkingSpotsMap.get(parkingSpotId);
    }

    @Override
    public ParkingSpot getParkingSpotByVehicleId(String  registeredVehicleNumber) {

        if(registeredVehicleNumber == null) {
            throw new IllegalArgumentException("registeredVehicleNumber cannot be null");
        }

        if(ticketMap == null || ticketMap.get(registeredVehicleNumber) == null) {
            throw new ParkingSpotNotFoundException("ParkingSpot with id " + registeredVehicleNumber + " not found");
        }

        Ticket ticket = ticketMap.get(registeredVehicleNumber);

        return getParkingSpotById(ticket.getParkingSpotId());
    }

    @Override
    public UUID getAvailableParkingSpot( ParkingSpotType parkingSpotType) {

        if (parkingSpotType == null) {
            throw new IllegalArgumentException("vehicleType cannot be null");
        }

        Queue<ParkingSpot> parkingSpots = availableParkingSpots.get(parkingSpotType);
        if(parkingSpots == null || parkingSpots.isEmpty()) {
            throw new ParkingSpotNotFoundException("No parking spots found for type " + parkingSpotType);
        }

        return parkingSpots.poll().getParkingSpotId();
    }

    @Override
    public Ticket generateTicket(UUID parkingSpotId, String registeredVehicleId) {

        if (parkingSpotId == null || registeredVehicleId == null) {
            throw new IllegalArgumentException("parkingSpotId and registeredVehicleId cannot be null");
        }

        ParkingSpot parkingSpot = getParkingSpotById(parkingSpotId);
        synchronized (parkingSpot) {

            Ticket ticket = Ticket.builder()
                    .ticketId(UUID.randomUUID())
                    .parkingSpotId(parkingSpotId)
                    .vehicleRegistrationNumber(registeredVehicleId)
                    .parkedAt(LocalDateTime.now())
                    .ticketStatus(TicketStatus.RESERVED)
                    .build();

            // TODO: in production ticket should be saved in database;
            //TODO: save ticket object in  database;
            parkingSpot.setVehicleId(registeredVehicleId);
            ticketMap.put(registeredVehicleId, ticket);

            return ticket;
        }
    }

    @Override
    public Ticket getTicketByRegisteredVehicleNumber(String registeredVehicleNumber) {

        if(registeredVehicleNumber == null) {
            throw new IllegalArgumentException("registeredVehicleNumber cannot be null");
        }

        if(ticketMap == null || ticketMap.get(registeredVehicleNumber) == null) {
            throw new TicketNotFoundException("Ticket with vehicle number " + registeredVehicleNumber + " not found");
        }

        return ticketMap.get(registeredVehicleNumber);
    }



    @Override
    public void unparkVehicle(ParkingSpot parkingSpot) {

        lock.lock();
        try{
            parkingSpot.setVehicleId(null);
            ParkingSpotType parkingSpotType = parkingSpot.getParkingSpotType();
            availableParkingSpots.get(parkingSpotType).add(parkingSpot);
        } finally {
            lock.unlock();
        }
    }
}




