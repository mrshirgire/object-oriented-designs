package com.ood.snakeLadder;

import lombok.Data;

@Data
public class Player {

    private String name;
    private int position;

    public Player(String playerName) {
        this.name = playerName;
    }
}
