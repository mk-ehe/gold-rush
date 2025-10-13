package edu.io;

public class Board {

    public Token[][] grid;
    public int size;

    public Board() {
        this(10);
    }

    public Board(int size) {
        this.size = size;
        grid = new Token[size][size];
        clean();
    }

    public void clean() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Token("・");
            }
        }
    }

    public void display() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col]+" ");
            }
            System.out.println();
        }
    }

    public Token square(int row, int col) {
        return grid[row][col];
    }

    public void placeToken(int row, int col, Token symbol) {
        if (square(row, col).label.equals("・")) {
            grid[row][col] = symbol;
        }
        else{
            System.out.println("Pole zajęte.");
        }
    }

    public static void main(String[] args) {
        Board board = new Board(10);
        board.clean();
        board.placeToken(0, 2, new Token("웃"));
        board.display();
    }
}