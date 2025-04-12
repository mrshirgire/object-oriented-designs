package com.ood.carrental;

import com.ood.carrental.enums.ReservationStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class Reservation {

    private int id;
    private int userId;
    private Timestamp reservationStartDate;
    private Timestamp reservationEndDate;
    private String vehicleId;
    private BigDecimal totalPrice;
    private String paymentId;
    private Location pickupLocation;
    private Location dropOffLocation;
    private ReservationStatus reservationStatus;

}
