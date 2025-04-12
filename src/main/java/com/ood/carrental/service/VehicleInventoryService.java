package com.ood.carrental.service;

import com.ood.carrental.exception.VehicleNotFound;
import com.ood.carrental.model.Vehicle;

import java.util.Map;
import java.util.Set;

public class VehicleInventoryService {

    Set<Vehicle> vehicles;
    Map<String, Vehicle> vehiclesMap;

    public void add(Vehicle vehicle) {

        if(vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        vehicles.add(vehicle);
        vehiclesMap.put(vehicle.getRegistrationNumber(), vehicle);
    }

    public Vehicle remove(String vehicleId) {

        if(vehicleId == null) {
            throw new IllegalArgumentException("VehicleId cannot be null");
        }

        if(!vehiclesMap.containsKey(vehicleId)) {
            throw new VehicleNotFound("Vehicle does not exist");
        }

        Vehicle vehicle = vehiclesMap.get(vehicleId);
        vehicles.remove(vehicle);
        vehiclesMap.remove(vehicleId);
        return vehicle;
    }

    public void update(Vehicle vehicle) {
        if(vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }

        if(!vehiclesMap.containsKey(vehicle.getRegistrationNumber())) {
            throw new VehicleNotFound("Vehicle does not exist");
        }

        vehiclesMap.put(vehicle.getRegistrationNumber(), vehicle);
        vehicles.add(vehicle);
    }

}
