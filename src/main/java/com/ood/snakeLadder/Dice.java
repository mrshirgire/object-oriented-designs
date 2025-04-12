package com.ood.snakeLadder;

import lombok.Data;

@Data
public class Dice {
    int value;
    int startRange;
    int endRange;

    public Dice(int startRange, int endRange) {
        this.startRange = startRange;
        this.endRange = endRange;
    }
}
