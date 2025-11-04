package edu.io.token;

import edu.io.Board;
import edu.io.Board.Coords;
import edu.io.player.Player;

public class PlayerToken extends Token {

    private Player player;
    private Board board;
    private int row;
    private int col;

    public PlayerToken(Player player, Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.player = player;
        this.board = board;
        Coords squareCoords = board.getAvailableSquare();
        this.row = squareCoords.row();
        this.col = squareCoords.col();
        board.placeToken(col, row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(row, col);
    }

    public enum Move {
        NONE,
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    public void move(Move dir) {

            int new_row = row;
            int new_col = col;

            switch (dir) {
                case UP : new_row--; break;
                case DOWN : new_row++; break;
                case LEFT : new_col--; break;
                case RIGHT : new_col++; break;
                case NONE : return;
                default : System.out.println("Unknown direction.");}

            if (new_row >= board.size || new_row < 0 || new_col >= board.size || new_col < 0) {
                throw new IllegalArgumentException("Cannot move outside the board!");
            }
                
            player.interactWithToken(board.peekToken(new_row, new_col));

            board.grid[row][col] = new EmptyToken();
            board.grid[new_row][new_col] = this;
            row = new_row;
            col = new_col;  
    }
}
