package com.sanket.designtictactoe.strategies.winning;

import com.sanket.designtictactoe.models.Board;
import com.sanket.designtictactoe.models.Cell;
import com.sanket.designtictactoe.models.Player;

public interface WinningStrategy {
    boolean checkIfWon(Board board, Cell cell, Player player);
}
