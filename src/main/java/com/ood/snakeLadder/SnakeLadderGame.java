package com.ood.snakeLadder;

import com.ood.elevator.dispatcher.Dispatcher;

import java.util.Scanner;

public class SnakeLadderGame {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while(true){

            Initializer initializer = new Initializer();
            GameData gameData = initializer.initializeGame(scanner);
            if(gameData == null){
                System.out.println("Game initialization failed. Please try again.");
                continue;
            }

            System.out.println("Game initialized successfully");
            BoardDisplay boardDisplay = new BoardDisplay();
            boardDisplay.printBoardGrid(gameData);

            GameRunner gameRunner = new GameRunner(gameData, boardDisplay);
            gameRunner.run();;

            System.out.println("Do you want to play again? (y/n)");
            String playAgain = scanner.next();
            if(playAgain.equalsIgnoreCase("n")){
                break;
            }
        }


    }
}
