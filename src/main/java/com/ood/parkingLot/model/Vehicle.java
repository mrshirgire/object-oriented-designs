package com.ood.parkingLot.model;

import com.ood.parkingLot.enums.VehicleType;
import lombok.Data;

import java.util.UUID;

@Data
public class Vehicle {

    private UUID uuid;
    private String vehicleRegistrationId;
    private VehicleType vehicleType;

    private UUID parkingSpotId;
}
