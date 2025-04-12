package com.ood.elevator.algorithm;

import java.util.concurrent.atomic.AtomicReference;

public class Node {

    int floorId;
    Node nextNode;

    public  Node(int floorId) {
        this.floorId = floorId;
    }
}
