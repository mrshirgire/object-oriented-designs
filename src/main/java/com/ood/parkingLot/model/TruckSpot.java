package com.ood.parkingLot.model;

import com.ood.parkingLot.enums.ParkingSpotType;
import lombok.Getter;

@Getter
public class TruckSpot extends ParkingSpot{

    Price price;
    public TruckSpot(Price price) {
        super(ParkingSpotType.TRUCK, price);
        this.price = price;
    }

}
