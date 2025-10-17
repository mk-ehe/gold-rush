package edu.io;

import edu.io.token.PlayerToken;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(10);
        board.clean();
        PlayerToken player = new PlayerToken(board, 0, 2);
        Board.Coords coords = player.pos();
        board.placeToken(coords.row(), coords.col(), player);
        board.display();
    }
}