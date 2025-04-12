package com.ood.parkingLot.exception;

public class ParkingSpotNotFoundException extends RuntimeException {

    String message;
    public ParkingSpotNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ParkingSpotNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
