package com.ood.snakeLadder;

import lombok.Data;

@Data
public class Cell {

    private int start;
    private int end;
    private CellType cellType;

    public Cell(int start, int end, CellType cellType) {
        this.start = start;
        this.end = end;
        this.cellType = cellType;
    }
}
