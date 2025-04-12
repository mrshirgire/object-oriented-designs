package com.ood.parkingLot.service;

import com.ood.parkingLot.model.ParkingSpot;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeQueue {

    AtomicReference<ParkingSpot> head = new AtomicReference<>();
    AtomicReference<ParkingSpot> tail = new AtomicReference<>();


    public void add(ParkingSpot parkingSpot) {

        while(true){
            if(tail.get() == null){
                head.set(parkingSpot);
                tail.set(parkingSpot);
                return;
            }


            ParkingSpot tailParkingSpot = tail.get();
            tailParkingSpot.setNext(parkingSpot);
            if(tail.compareAndSet(tailParkingSpot, parkingSpot)){
                break;
            }
        }
    }

    public ParkingSpot poll() {

        if (head.get() == null) {
            return null;
        }


        while (true) {

            ParkingSpot currentParkingSpotHead = head.get();
            ParkingSpot nextParkingSpotHead = head.get().getNext();
            if (head.compareAndSet(currentParkingSpotHead, nextParkingSpotHead)){
                currentParkingSpotHead.setNext(null);
                return currentParkingSpotHead;
            }

        }

    }
}

