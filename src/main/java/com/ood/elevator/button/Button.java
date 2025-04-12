package com.ood.elevator.button;

import com.ood.elevator.enums.ButtonType;
import lombok.Data;

@Data
public class Button {

    private String label;
    private ButtonType buttonType;
    private boolean isPressed;
    Button(ButtonType buttonType, String label) {
        this.buttonType = buttonType;
        this.label = label;
    }
}
