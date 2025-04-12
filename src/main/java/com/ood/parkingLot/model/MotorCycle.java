package com.ood.parkingLot.model;

import com.ood.parkingLot.enums.ParkingSpotType;

public class MotorCycle extends ParkingSpot{

    Price price;
    public MotorCycle(Price price) {
        super(ParkingSpotType.MOTORCYCLE, price);
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }
}
