package com.sanket.designtictactoe.models;

import lombok.Data;

@Data
public class Move {
    private Cell cell;
    private Player player;
}
