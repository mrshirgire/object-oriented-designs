package com.ood.elevator.dispatcher;

import com.ood.elevator.ElevatorController;
import com.ood.elevator.enums.DispatcherType;

public class DispatchFactory {


    public Dispatcher getDispatcher(DispatcherType dispatcherType, ElevatorController elevatorController) {

        switch (dispatcherType) {
            case EXTERNAL -> {
                return new ExternalDispatcher(elevatorController);
            }
            case INTERNAL -> {
                return new InternalDispatcher(elevatorController);
            }
        }

        return null;
    }

}
