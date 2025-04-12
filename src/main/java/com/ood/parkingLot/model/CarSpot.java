package com.ood.parkingLot.model;

import com.ood.parkingLot.enums.ParkingSpotType;
import lombok.Getter;

@Getter
public class CarSpot extends ParkingSpot {

    private final Price price;

    public CarSpot(Price price) {
        super(ParkingSpotType.CAR, price);
        this.price = price;
    }

}


