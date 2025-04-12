package com.ood.elevator.dispatcher;

import com.ood.elevator.enums.Direction;

public interface Dispatcher {

    void dispatch(int elevatorId, int floorId, Direction direction);
}
