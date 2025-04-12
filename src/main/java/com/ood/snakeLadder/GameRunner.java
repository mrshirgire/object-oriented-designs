package com.ood.snakeLadder;

import java.util.Deque;

public class GameRunner {


    private final GameData gameData;
    BoardDisplay boardDisplay;
    GameRunner(GameData gameData, BoardDisplay boardDisplay){
        this.gameData = gameData;
        this.boardDisplay = boardDisplay;
    }

    public void run(){

        Deque<Player> playerList = gameData.getPlayers();

        while(true){


            Player player = playerList.getFirst();
            System.out.println("Player: "+player.getName());
            int totalDiceValue = 0;
            for(int i = 0; i < gameData.getDices().length; i++){
                Dice dice = gameData.getDices()[i];
                int startRage = dice.getStartRange();
                int endRange = dice.getEndRange();
                int diceValue = (int) (Math.random() * (endRange - startRage + 1)) + startRage;
                System.out.println("Dice value: "+diceValue);
                totalDiceValue += diceValue;
            }

            System.out.println("Total Dice Value: "+totalDiceValue);
            int playerNextPosition = player.getPosition() + totalDiceValue;

            // check if playerNextPosition is greater than board length
            if(playerNextPosition >= gameData.getBoard().length){
                System.out.println("Player: "+player.getName()+" has won the game");
                break;
            }

            Cell currCell = gameData.getBoard()[playerNextPosition];
            if(currCell == null){
                System.out.println("No snake or ladder at position: "+playerNextPosition);
                System.out.println("Player: "+player.getName()+" has moved to position: "+playerNextPosition);
                player.setPosition(playerNextPosition);
            }else if(currCell.getCellType() == CellType.SNAKE){
                System.out.println("Player: "+player.getName()+" has hit a snake");
                int snakeEnd = currCell.getEnd();
                player.setPosition(snakeEnd);
                System.out.println("Player: "+player.getName()+" has moved to position: "+snakeEnd);
            }else if(currCell.getCellType() == CellType.LADDER){
                System.out.println("Player: "+player.getName()+" has hit a ladder");
                int ladderEnd = currCell.getEnd();
                player.setPosition(ladderEnd);
                System.out.println("Player: "+player.getName()+" has moved to position: "+ladderEnd);
            }

            boardDisplay.printBoardGrid(gameData);
            playerList.removeFirst();
            playerList.addLast(player);
        }
    }
}
