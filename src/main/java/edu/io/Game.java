package edu.io;
import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import java.util.Scanner;
import edu.io.player.Player;

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
        board.placeToken(4, 4, new GoldToken());

        while (true) {
            System.out.println("\nGold amount: "+player.gold.amount());
            board.display();
            System.out.print("Moves: W -> UP, S -> DOWN, A -> LEFT, D -> RIGHT, Anything else -> pass: ");
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
        }
    }
}