package com.ood.parkingLot.factory;

import com.ood.parkingLot.enums.ParkingSpotType;
import com.ood.parkingLot.model.*;

public class ParkingSpotFactory {

    public ParkingSpot getParkingSpot(ParkingSpotType parkingSpotType, Price price) {

        if(parkingSpotType == null) {
            throw new IllegalArgumentException("Parking Spot type cannot be null");
        }

        return switch (parkingSpotType) {
            case CAR -> new CarSpot(price);
            case TRUCK -> new TruckSpot(price);
            case MOTORCYCLE -> new MotorCycle(price);
            default -> throw new IllegalArgumentException("Unsupported parking spot type: " + parkingSpotType);
        };
    }
}
