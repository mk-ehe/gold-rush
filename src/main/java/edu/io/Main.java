package edu.io;

import java.util.Scanner;
import edu.io.token.PlayerToken;

public class Main {
    public static void main(String[] args) {

        Board board = new Board(10);
        PlayerToken player = new PlayerToken(board, 2, 2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            board.display();
            System.out.print("Choose direction: (up, down, left, right, none): ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                PlayerToken.Move move = PlayerToken.Move.valueOf(input);
                player.move(move);
                System.out.println("Player moved to: " + player.pos());
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown direction! Try again.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}