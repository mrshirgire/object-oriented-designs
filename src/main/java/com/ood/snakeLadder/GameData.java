package com.ood.snakeLadder;

import lombok.Builder;
import lombok.Getter;

import java.util.Deque;

@Getter
@Builder
class GameData {
    Cell[] board;
    Deque<Player> players;
    Dice[] dices;
    int noOfRows;
    int noOfCols;
}