package com.ood.elevator.algorithm;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeQueue {

    AtomicReference<Node> head;
    AtomicReference<Node> tail;

    public void add(int floorId) {

        Node newTail = new Node(floorId);
        if (head.get() == null) {
            head.set(newTail);
            tail.set(newTail);
            return;
        }

       while(true) {

           Node currTail = tail.get();
           currTail.nextNode = newTail;
           if(tail.compareAndSet(currTail, newTail)){
               break;
           }
       }
    }

    public int remove() {
        while(true) {

            if(head.get() == null) {
                return -1;
            }

            Node currHead = head.get();
            Node newHead = currHead.nextNode;
            if(head.compareAndSet(currHead, newHead)) {
                return currHead.floorId;
            }
        }

    }
}
