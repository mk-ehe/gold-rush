package edu.io;

public class Main {
    public static void main(String[] args) {
        System.out.println("Gold Rush");
        Board board = new Board(10);
        board.clean();
        board.placeToken(0, 2, new Token("웃"));
        board.display();
    }
}