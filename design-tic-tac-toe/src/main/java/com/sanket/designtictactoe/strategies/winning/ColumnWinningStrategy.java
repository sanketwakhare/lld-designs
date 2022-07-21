package com.sanket.designtictactoe.strategies.winning;

import com.sanket.designtictactoe.models.Board;
import com.sanket.designtictactoe.models.Cell;
import com.sanket.designtictactoe.models.Player;

import java.util.List;

public class ColumnWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkIfWon(Board board, Cell cell, Player player) {

        int column = cell.getColumn();
        for (int currRow = 0; currRow < board.getDimension(); currRow++) {
            Cell currCell = board.getGrid().get(currRow).get(column);
            if (!player.getSymbol().equals(currCell.getSymbol())) {
                return false;
            }
        }
        return true;
    }
}
