package com.ood.carrental.exception;

public class VehicleNotFound extends RuntimeException {

    public VehicleNotFound(String message) {
        super(message);
    }

    public VehicleNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
