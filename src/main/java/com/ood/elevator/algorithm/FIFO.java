package com.ood.elevator.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class FIFO {

    Queue<Integer> queue;
    public FIFO() {
        queue = new LinkedList<>();
    }

    public void fifo() {

        while (!queue.isEmpty()) {
            int floor = queue.poll();

        }
    }




}
