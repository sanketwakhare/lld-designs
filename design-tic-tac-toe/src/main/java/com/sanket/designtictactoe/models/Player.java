package com.sanket.designtictactoe.models;

import lombok.Data;

@Data
public abstract class Player {
    private Long id;
    protected String name;
    protected Symbol symbol;
    protected PlayerType playerType;

    public Player(PlayerType playerType, Symbol symbol) {
        this.playerType = playerType;
        this.symbol = symbol;
    }

    public abstract Move makeMove(Board board);
}
