package com.ood.elevator.dispatcher;

import com.ood.elevator.ElevatorController;
import com.ood.elevator.enums.Direction;

public class InternalDispatcher implements Dispatcher {

    ElevatorController elevatorController;
    public InternalDispatcher(ElevatorController elevatorController) {
        this.elevatorController = elevatorController;
    }

    @Override
    public void dispatch(int elevatorId, int floorId, Direction direction) {
        elevatorController.moveElevator(elevatorId, floorId, direction);
    }
}
