package com.ood.parkingLot.model;

import com.ood.parkingLot.enums.ParkingSpotType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
//@Builder
public class ParkingSpot {

    private final UUID parkingSpotId;
    private String vehicleId;
    private final ParkingSpotType parkingSpotType;
    private final Price price;

    ParkingSpot next;

    ParkingSpot(ParkingSpotType parkingSpotType, Price price) {
        this.parkingSpotId = UUID.randomUUID();
        this.parkingSpotType = parkingSpotType;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "parkingSpotId=" + parkingSpotId +
                ", vehicleId='" + vehicleId + '\'' +
                ", PARKING_SPOT_TYPE=" + parkingSpotType +
                ", price=" + price.getAmount() + price.getCurrency() +
                '}';
    }
}

