package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

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

    public int size() {
        return size;
    }

    public record Coords(int row, int col) {
    }

    public void clean() {
        EmptyToken emp = new EmptyToken();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = emp;
            }
        }
    }

    public void display() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col].label() + " ");
            }
            System.out.println();
        }
    }

    public Token peekToken(int col, int row) {
        return grid[row][col];
    }

    public void placeToken(int col, int row, Token symbol) {
        Token current = grid[row][col];
        if (current instanceof EmptyToken) {
            grid[row][col] = symbol;
        } else {
            System.out.println("Field already taken.");
        }
    }

    public Coords getAvailableSquare() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] instanceof EmptyToken) {
                    return new Coords(row, col);
                }
            }
        }
        throw new IllegalStateException("Field is not available.");
    }
}