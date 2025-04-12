package com.ood.elevator;

import com.ood.elevator.enums.Direction;
import com.ood.elevator.model.ElevatorCar;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ElevatorController {

    Map<Integer, ElevatorCar> elevatorCars;
    ElevatorController(Map<Integer, ElevatorCar> elevatorCars){
        this.elevatorCars = elevatorCars;
    }

    public void moveElevator(int elevatorId, int floorNumber, Direction direction){

        ElevatorCar elevatorCar = elevatorCars.get(elevatorId);
        if(elevatorCar == null){
            throw new IllegalArgumentException("Elevator id " + elevatorId + " not found");
        }

        processRequest(floorNumber, direction, elevatorCar);
    }

    private void processRequest(int requestedFloorId, Direction requestedDirection, ElevatorCar elevatorCar){

        int currentElevatorCarFloor = elevatorCar.getDisplay().getCurrFloorId();

        PriorityQueue<Integer> upRequestQueue = elevatorCar.getUpRequestQueue();
        PriorityQueue<Integer> downRequestQueue = elevatorCar.getDownRequestQueue();
        PriorityQueue<Integer> pendingRequestQueue = elevatorCar.getPendingRequestQueue();

        if(requestedDirection.equals(Direction.UP)){

            if(currentElevatorCarFloor < requestedFloorId){
                upRequestQueue.add(requestedFloorId);
            }else{
                pendingRequestQueue.add(requestedFloorId);
            }
        }else if(requestedDirection.equals(Direction.DOWN)){

            if(currentElevatorCarFloor > requestedFloorId){
                downRequestQueue.add(requestedFloorId);
            }else{
                pendingRequestQueue.add(requestedFloorId);
            }
        }
    }





}
