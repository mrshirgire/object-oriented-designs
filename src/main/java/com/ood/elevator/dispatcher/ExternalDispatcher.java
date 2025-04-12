package com.ood.elevator.dispatcher;

import com.ood.elevator.ElevatorController;
import com.ood.elevator.enums.Direction;

public class ExternalDispatcher implements Dispatcher {

    ElevatorController elevatorController;
    ExternalDispatcher(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    @Override
    public void dispatch(int elevatorId, int floorId, Direction direction) {

        if(elevatorId < 1 || elevatorId > 3) {
            throw new IllegalArgumentException("elevatorId must be between 0 and 3");
        }

        if(floorId < 1 || floorId > 100) {
            throw new IllegalArgumentException("floorId must be between 0 and 100");
        }

        if(direction == null) {
            throw new IllegalArgumentException("direction cannot be null");
        }


        elevatorController.moveElevator(elevatorId, floorId, direction);
    }
}
