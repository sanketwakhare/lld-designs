package com.sanket.designtictactoe.strategies.botplaying;

import com.sanket.designtictactoe.models.Board;
import com.sanket.designtictactoe.models.Cell;
import com.sanket.designtictactoe.models.Move;
import com.sanket.designtictactoe.models.Player;

import java.util.List;
import java.util.Objects;

public class EasyDifficultyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeNextMove(Board board, Player player) {

        for (int i = 0; i < board.getGrid().size(); i++) {
            List<Cell> row = board.getGrid().get(i);
            for (Cell cell : row) {
                if (Objects.isNull(cell.getSymbol())) {
                    cell.setSymbol(player.getSymbol());
                    Move move = new Move();
                    move.setPlayer(player);
                    move.setCell(cell);
                    return move;
                }
            }
        }
        return null;
    }
}
