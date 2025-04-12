package com.ood.snakeLadder;

public class BoardDisplay {

    public void printBoardGrid(GameData gameData) {

        final int ROWS = gameData.getNoOfRows();
        final int COLS = gameData.getNoOfCols();

        System.out.println("\n=== Current Board State ===");

        for (int row = ROWS - 1; row >= 0; row--) {
            if (row % 2 == 0) {  // Even row, print right to left
                for (int col = COLS - 1; col >= 0; col--) {
                    int pos = row * COLS + col;
                    printCell(gameData, pos);
                }
            } else {  // Odd row, print left to right
                for (int col = 0; col < COLS; col++) {
                    int pos = row * COLS + col;
                    printCell(gameData, pos);
                }
            }
            System.out.println();
        }

        System.out.println("Legend: [S]=Snake, [L]=Ladder, [X]=Player initial\n");
    }

    private void printCell(GameData gameData, int position) {
        StringBuilder cell = new StringBuilder();

        // Base position
        cell.append(String.format("%3d", position));

        // Snake or Ladder
        Cell cellData = gameData.getBoard()[position];
        if (cellData != null) {
            if (cellData.getCellType() == CellType.SNAKE) {
                cell.append("[S]");
            } else if (cellData.getCellType() == CellType.LADDER) {
                cell.append("[L]");
            }
        }

        // Players on this cell
        for (Player player : gameData.getPlayers()) {
            if (player.getPosition() == position) {
                cell.append("[").append(player.getName().charAt(0)).append("]");
            }
        }

        // Print the final composed cell
        System.out.print(cell + "\t");
    }

}
