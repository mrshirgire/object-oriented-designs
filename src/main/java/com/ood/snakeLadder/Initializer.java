package com.ood.snakeLadder;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Initializer {


    public void initializeSnakes(Cell[] board, Scanner scanner, int noOfCols, int noOfRows){
        System.out.println("Enter no of snake, should be less than 10: ");
        int noOfSnakes = scanner.nextInt();
        for(int i = 1; i <= noOfSnakes; i++){

//            System.out.println("Enter snake's start (e.g., 3 4): ");
//            int startRow =  scanner.nextInt();
//            int startCol =  scanner.nextInt();

            int startRow =  (int) (Math.random() * noOfRows);
            int startCol =  (int) (Math.random() * noOfCols);

            int start = (startRow * noOfCols) + startCol;

//            System.out.println("Enter snake's end (e.g., 3 4): ");
//            int endRow = scanner.nextInt();
//            int endCol = scanner.nextInt();
            int end = Integer.MAX_VALUE;
            while(end > start){
                int endRow =  (int) (Math.random() * noOfRows);
                int endCol =  (int) (Math.random() * noOfCols);
                end = (endRow * noOfCols) + endCol;
            }

            Cell snake = CellFactory.getCell(start, end, CellType.SNAKE);
            board[start] = snake;
        }

    }

    public void initializeLadder(Cell[] board, Scanner scanner, int noOfCols, int noOfRows){
        System.out.println("Enter no of ladder, should be less than 10: ");
        int noOfLadders = scanner.nextInt();
        for(int i = 1; i <= noOfLadders; i++){

//            System.out.println("Enter ladder's start (e.g., 3 4): ");
//            int startRow = scanner.nextInt();
//            int startCol = scanner.nextInt();

            int startRow =  (int) (Math.random() * noOfRows);
            int startCol =  (int) (Math.random() * noOfCols);
            int start = (startRow * noOfCols) + startCol;;
            while(board[start] != null && board[start].getCellType() == CellType.SNAKE){
                startRow =  (int) (Math.random() * noOfRows);
                startCol =  (int) (Math.random() * noOfCols);
                start  = (startRow * noOfCols) + startCol;
            }

//            System.out.println("Enter ladder's end (e.g., 3 4): ");
//            int endRow = scanner.nextInt();
//            int endCol = scanner.nextInt();
//            int end = (endRow * noOfCols) + endCol;

            int end = Integer.MIN_VALUE;
            while(end < start){
                int endRow =  (int) (Math.random() * noOfRows);
                int endCol =  (int) (Math.random() * noOfCols);
                end = (endRow * noOfCols) + endCol;
            }

            Cell ladder = CellFactory.getCell(start, end, CellType.LADDER);
            board[start] = ladder;
        }

    }

    public void initializePlayers(Deque<Player> players, int noOfPlayers, Scanner scanner){
        for(int i = 0; i < noOfPlayers; i++){
            System.out.println("Enter player name: ");
            String playerName = scanner.next();
            players.add(new Player(playerName));
        }
    }

    private void initializeDices(Dice[] dices, Scanner scanner) {

        for(int i = 0; i < dices.length; i++){
            System.out.println("Enter dice start and end range (e.g., 0 6):");
            int start = scanner.nextInt();
            int end = scanner.nextInt();

            Dice dice = new Dice(start, end);
            dices[i] = dice;
        }
    }


    public GameData initializeGame(Scanner scanner) {

        System.out.println("Enter board dimension: ");
        String dimensionString = scanner.next();
        String[] dimensions = dimensionString.split("\\*");

        int noOfRows = Integer.parseInt(dimensions[0]);
        int noOfCols = Integer.parseInt(dimensions[1]);

        Cell[] board = new Cell[noOfRows * noOfCols];
        initializeSnakes(board, scanner, noOfCols, noOfRows);
        initializeLadder(board, scanner, noOfCols, noOfRows);

        System.out.println("Enter no of players: ");
        int noOfPlayers = scanner.nextInt();
        Deque<Player> players = new LinkedList<>();
        initializePlayers(players, noOfPlayers, scanner);


        System.out.println("Enter no of dices: ");
        int noOfDices = scanner.nextInt();
        Dice[] dices = new Dice[noOfDices];
        initializeDices(dices, scanner);

        GameData gameData = GameData.builder()
                .board(board)
                .players(players)
                .dices(dices)
                .noOfRows(noOfRows)
                .noOfCols(noOfCols)
                .build();
        return gameData;
    }




}
