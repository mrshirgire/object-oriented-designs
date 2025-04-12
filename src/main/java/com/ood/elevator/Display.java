package com.ood.elevator;

import com.ood.elevator.enums.Direction;
import lombok.Data;

@Data
public class Display {

    int currFloorId;
    Direction direction;
    Display(int floorId, Direction direction) {
        this.currFloorId = floorId;
        this.direction = direction;
    }
}
