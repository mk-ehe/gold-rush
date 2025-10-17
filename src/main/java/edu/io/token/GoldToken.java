package edu.io.token;

public class GoldToken extends Token {

    public int size;

    public GoldToken() {
         super(Label.GOLD_TOKEN_LABEL);
    }

    public GoldToken(int size) {
        super(Label.GOLD_TOKEN_LABEL);
        this.size = size;
    }
}
