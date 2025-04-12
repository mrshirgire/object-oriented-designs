package com.ood.elevator.button;


import com.ood.elevator.enums.ButtonType;

public class Number extends Button{

    public Number(String floorNumber) {
        super(ButtonType.NUMBER, floorNumber);
    }


}
