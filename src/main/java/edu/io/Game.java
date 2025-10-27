package edu.io;
import edu.io.token.PlayerToken;
import java.util.Scanner;

public class Game {
    private Board board;
    private Player player;

    public Game() {
        this.board = new Board();
    }

    public void join(Player player) {
        this.player = player;
        PlayerToken playerToken = new PlayerToken(player, board);
        player.assignToken(playerToken);
    }

    public void start() {
        Scanner input = new Scanner(System.in);
        board.display();

        while (true) {
            System.out.print("\nMoves: W -> UP, S -> DOWN, A -> LEFT, D -> RIGHT, Anything else -> pass: ");
            String direction = input.nextLine().toUpperCase();

            try {
                switch (direction) {
                    case "W": player.token().move(PlayerToken.Move.UP); break;
                    case "S": player.token().move(PlayerToken.Move.DOWN); break;
                    case "A": player.token().move(PlayerToken.Move.LEFT); break;
                    case "D": player.token().move(PlayerToken.Move.RIGHT); break;
                    default : player.token().move(PlayerToken.Move.NONE); break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Cannot move outside the board!");
            }
            board.display();
        }
    }
}