package com.sanket.designtictactoe.controller;

import com.sanket.designtictactoe.exceptions.InvalidUndoOperationException;
import com.sanket.designtictactoe.models.*;
import com.sanket.designtictactoe.strategies.winning.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        return new Game.Builder()
                .setBoard(new Board(dimension))
                .setPlayers(players)
                .setMovesPerformed(new ArrayList<>())
                .setWinningStrategies(winningStrategies)
                .setLastPlayerMovedIndex(-1)
                .setGameStatus(GameStatus.IN_PROGRESS)
                .build();
    }

    public void makeMove(Game game) throws InvalidUndoOperationException {
        game.makeMove();
    }

    public void undoMove(Game game) throws InvalidUndoOperationException {
        game.undo();
    }

    public void displayBoard(Game game) {
        game.getBoard().printBoard();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }


}
