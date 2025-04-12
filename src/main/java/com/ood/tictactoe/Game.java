package com.ood.tictactoe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Game {

    PlayingPiece[][] board = new PlayingPiece[3][3];
    Queue<Player> players = new LinkedList<>();


    public static void main(String[] args) {

        Game game = new Game();
        PlayingPiece xPlayingPiece = new XPlayingPiece();
        PlayingPiece oPlayingPiece = new OPlayingPiece();
        Player player1 = new Player(xPlayingPiece);
        Player player2 = new Player(oPlayingPiece);
        game.players.add(player1);
        game.players.add(player2);




    }
}
