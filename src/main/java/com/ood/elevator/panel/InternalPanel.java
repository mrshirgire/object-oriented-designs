package com.ood.elevator.panel;

import com.ood.elevator.button.Button;

import java.util.List;

public class InternalPanel extends Panel {

    public InternalPanel(int elevatorId, List<Button> floorNumberButtons, Button openDoorButton,
                         Button closeDoorButton) {
        super(elevatorId, floorNumberButtons, openDoorButton, closeDoorButton);
    }
}
