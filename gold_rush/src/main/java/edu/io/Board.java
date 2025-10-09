package edu.io;

public class Board {

    public Token[][] grid;

    public Board(int size) {
        grid = new Token[size][size];
    }

    public void clean() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Token("\u30FB");
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

    public String square(int row, int col) {
        return grid[row][col].toString();
    }

    public void placeToken(int row, int col, String symbol) {
        if (square(row, col).equals("\u30FB")) {
            grid[row][col] = new Token(symbol);
        }
        else{
            System.out.println("Pole zajęte.");
        }
    }

    public static void main(String[] args) {
        Board board = new Board(10);
        board.clean();
        board.placeToken(0, 2, "\uD83D\uDCB0");
        board.display();
    }
}