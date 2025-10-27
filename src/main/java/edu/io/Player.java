package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Player {
    private PlayerToken player;
    private double gold;

    public void assignToken(PlayerToken player) {
        this.player = player;
    }

    public PlayerToken token() {
        return player;
    }

    public double gold() {
        return gold;
    }

    public void gainGold(double gold) {
        if (gold >= 0) {
            this.gold += gold;
        } else {
            throw new IllegalArgumentException("Gold amount must be greater than 0.");
        }
    }

    public void loseGold(double gold) {
        if (gold >= 0) {
            if (this.gold - gold >= 0) {
                this.gold -= gold;
            } else {
                throw new IllegalArgumentException("Gold amount must be greater than 0.");
            }
        } else {
            throw new IllegalArgumentException("Gold amount cannot go below zero");
        }
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            System.out.println("Found some gold!");
            gainGold(goldToken.amount());
        }
    }
}
