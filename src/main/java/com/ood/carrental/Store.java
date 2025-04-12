package com.ood.carrental;

import com.ood.parkingLot.payment.PaymentContext;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Store {

    private UUID storeId;
    VehicleInventoryManager vehicleInventoryManager;
    Location location;
    PaymentContext paymentContext;
    List<Reservation> reservations;

}
