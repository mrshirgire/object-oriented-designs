package com.ood.carrental.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Vehicle {

    private String registrationNumber;
    private String model;
    private int kmDriven;

    Vehicle(String registrationNumber, String model, int kmDriven) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.kmDriven = kmDriven;
    }



}
