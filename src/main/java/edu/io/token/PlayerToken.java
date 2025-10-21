package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token {

    public Board board; 
    public int col;       
    public int row;      

    public PlayerToken(Board board) {
        this(board, 0, 0);
    }

    public PlayerToken(Board board, int row, int col) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.row = row;
        this.col = col;
        if (board.grid[row][col] instanceof EmptyToken) {
        board.grid[row][col] = this;
        }
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

            board.grid[row][col] = new EmptyToken();
            board.grid[new_row][new_col] = this;
            row = new_row;
            col = new_col;
            
    }
}
