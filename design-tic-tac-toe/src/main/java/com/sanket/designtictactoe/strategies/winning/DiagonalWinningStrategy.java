package com.sanket.designtictactoe.strategies.winning;

import com.sanket.designtictactoe.models.Board;
import com.sanket.designtictactoe.models.Cell;
import com.sanket.designtictactoe.models.Player;

public class DiagonalWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkIfWon(Board board, Cell cell, Player player) {
        return checkLeftDiagonal(board, cell, player) || checkRightDiagonal(board, cell, player);
    }

    public boolean checkRightDiagonal(Board board, Cell cell, Player player) {
        // traverse right diagonal
        for (int row = 0; row < board.getDimension(); row++) {
            Cell currCell = board.getGrid().get(row).get(row);
            if (!player.getSymbol().equals(currCell.getSymbol())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkLeftDiagonal(Board board, Cell cell, Player player) {
        // traverse left diagonal
        int column = board.getDimension() - 1;
        for (int row = 0; row < board.getDimension(); row++) {
            Cell currCell = board.getGrid().get(row).get(column--);
            if (!player.getSymbol().equals(currCell.getSymbol())) {
                return false;
            }
        }
        return true;
    }
}
