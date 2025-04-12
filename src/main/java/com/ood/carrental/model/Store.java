package com.ood.carrental.model;

import com.ood.carrental.service.VehicleInventoryService;
import com.ood.parkingLot.payment.PaymentContext;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Store {

    private UUID storeId;
    VehicleInventoryService vehicleInventoryService;
    Location location;
    PaymentContext paymentContext;
    List<Reservation> reservations;

}
