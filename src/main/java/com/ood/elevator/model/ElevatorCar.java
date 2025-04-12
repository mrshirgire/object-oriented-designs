package com.ood.elevator.model;

import com.ood.elevator.Display;
import com.ood.elevator.enums.Direction;
import com.ood.elevator.enums.ElevatorStatus;
import com.ood.elevator.panel.InternalPanel;
import lombok.Data;

import java.util.*;

@Data
public class ElevatorCar {

    private int elevatorId;
    private InternalPanel internalPanel;
    private Display display;
    private int floorId;
    private Direction direction;
    private ElevatorStatus elevatorStatus;
    private Door door;

    private PriorityQueue<Integer> upRequestQueue;
    private PriorityQueue<Integer> downRequestQueue;
    private PriorityQueue<Integer> pendingRequestQueue;

    public ElevatorCar(int elevatorId, InternalPanel internalPanel, Display display) {
        this.elevatorId = elevatorId;
        this.internalPanel = internalPanel;
        this.display = display;
        upRequestQueue = new PriorityQueue<>();
        downRequestQueue = new PriorityQueue<>(Collections.reverseOrder());
        pendingRequestQueue = new PriorityQueue<>();
    }

    public void move() throws InterruptedException {
        while(true){
            if(this.direction.equals(Direction.UP)){
                System.out.println("stop at floor: "+upRequestQueue.poll());
                Thread.sleep(1000);
            }else if(this.direction.equals(Direction.DOWN)){
                System.out.println("stop at floor: "+downRequestQueue.poll());
                Thread.sleep(1000);
            }
        }
    }
}
