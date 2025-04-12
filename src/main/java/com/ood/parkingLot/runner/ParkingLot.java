package com.ood.parkingLot.runner;

import com.ood.parkingLot.enums.ParkingSpotType;
import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.service.ParkingSpotManager;

import java.util.UUID;

public class ParkingLot {

    ParkingSpotManager parkingSpotManager;
    private ParkingLot(){
        throw new IllegalStateException("can't initiate object outside Instance of ParkingLot already created.");
    }

    private static class ParkingLotHolder {
        private static final ParkingLot INSTANCE = new ParkingLot();
    }

    public static ParkingLot getInstance() {
        return ParkingLotHolder.INSTANCE;
    }

    void initialization(){

//        parkingSpotManager = ParkingSpotManager(Queue<ParkingSpot > availableParkingSpots, Map< UUID, ParkingSpot> parkingSpotsMap,Map< ParkingSpotType,
//                List<ParkingSpot>> parkingSpotsTypeMap);
    }


}
