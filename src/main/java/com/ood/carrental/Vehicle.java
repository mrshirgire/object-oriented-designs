package com.ood.carrental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Vehicle {

    private String registrationNumber;
    private String model;
    private int kmDriven;



}
