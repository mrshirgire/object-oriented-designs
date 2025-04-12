package com.ood.snakeLadder;

public class CellFactory {


    public static Cell getCell(int start, int end, CellType cellType) {

        return switch (cellType) {
            case LADDER -> new Ladder(start, end);
            case SNAKE -> new Snake(start, end);
            default -> throw new IllegalArgumentException("Invalid cell type: " + cellType);
        };
    }
}
