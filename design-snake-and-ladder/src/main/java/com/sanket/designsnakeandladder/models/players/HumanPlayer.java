package com.sanket.designsnakeandladder.models.players;

public class HumanPlayer extends Player {

    public HumanPlayer(ColorType colorType, int buttonCount) {
        super(PlayerType.HUMAN, colorType, buttonCount);
    }
}
