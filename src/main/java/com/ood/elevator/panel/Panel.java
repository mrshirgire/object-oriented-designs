package com.ood.elevator.panel;

import com.ood.elevator.button.Button;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class Panel {

    private int elevatorId;
    private List<Button> floorNumberButtons;
    private Button upButton;
    private Button downButton;
    private Button openDoorButton;
    private Button closeDoorButton;

    public Panel(int elevatorId, Button upButton, Button downButton) {
        this.elevatorId = elevatorId;
        this.upButton = upButton;
        this.downButton = downButton;
    }

    public Panel(int elevatorId, List<Button> floorNumberButtons, Button openDoorButton, Button closeDoorButton) {
        this.elevatorId = elevatorId;
        this.floorNumberButtons = floorNumberButtons;
        this.openDoorButton = openDoorButton;
        this.closeDoorButton = closeDoorButton;
    }



}
