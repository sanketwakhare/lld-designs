package com.sanket.designtictactoe.strategies.winning;

import com.sanket.designtictactoe.models.Board;
import com.sanket.designtictactoe.models.Cell;
import com.sanket.designtictactoe.models.Player;
import com.sanket.designtictactoe.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkIfWon(Board board, Cell cell, Player player) {

        List<Cell> row = board.getGrid().get(cell.getRow());
        for (Cell currCell : row) {
            if (!player.getSymbol().equals(currCell.getSymbol())) {
                return false;
            }
        }
        return true;
    }
}
