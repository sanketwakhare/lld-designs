package com.sanket.designtictactoe.strategies.botplaying;

import com.sanket.designtictactoe.models.Board;
import com.sanket.designtictactoe.models.Cell;
import com.sanket.designtictactoe.models.Move;
import com.sanket.designtictactoe.models.Player;

public interface BotPlayingStrategy {
    public Move makeNextMove(Board board, Player player);
}
