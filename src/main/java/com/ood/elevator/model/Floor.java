package com.ood.elevator.model;

import com.ood.elevator.Display;
import com.ood.elevator.panel.Panel;
import lombok.Data;

import java.util.List;

@Data
public class Floor {

    int floorId;
    List<Display>  externalDisplays;
    List<Panel> externalPanels;
    public Floor(int floorId, List<Display> externalDisplays, List<Panel> externalPanels) {
        this.floorId = floorId;
        this.externalDisplays = externalDisplays;
        this.externalPanels = externalPanels;
    }

}
