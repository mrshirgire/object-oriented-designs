package com.ood.carrental.model;

import lombok.Data;

@Data
public class Location {

    private int id;
    private String city;
    private String country;
    private String state;
    private String zip;

}
