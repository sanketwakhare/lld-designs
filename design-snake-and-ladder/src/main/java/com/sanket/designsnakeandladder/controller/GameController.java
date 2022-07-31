package com.sanket.designsnakeandladder.controller;

import com.sanket.designsnakeandladder.models.Board;
import com.sanket.designsnakeandladder.models.Dice;
import com.sanket.designsnakeandladder.models.Game;
import com.sanket.designsnakeandladder.models.GameStatus;
import com.sanket.designsnakeandladder.models.foreignentities.ForeignEntity;
import com.sanket.designsnakeandladder.models.players.ColorType;
import com.sanket.designsnakeandladder.models.players.HumanPlayer;
import com.sanket.designsnakeandladder.models.players.Player;
import com.sanket.designsnakeandladder.models.players.PlayerType;
import com.sanket.designsnakeandladder.strategies.HandleMoveStrategy;
import com.sanket.designsnakeandladder.strategies.UnlockButtonStrategy;

import java.util.List;

public class GameController {

    // create Game

    public Game createGame(int boardDimension,
                           List<ForeignEntity> foreignEntities,
                           List<Player> players,
                           int totalButtonsPerPlayer,
                           Dice dice,
                           HandleMoveStrategy handleMoveStrategy,
                           List<UnlockButtonStrategy> unlockButtonStrategies) {
        return new Game.Builder()
                .setBoard(new Board(boardDimension, foreignEntities))
                .setPlayers(players)
                .setDice(dice)
                .setButtonsPerPlayer(totalButtonsPerPlayer)
                .setHandleMoveStrategy(handleMoveStrategy)
                .setUnlockButtonStrategies(unlockButtonStrategies)
                .build();
    }

    public void startGame(Game game) {
        game.setGameStatus(GameStatus.IN_PROGRESS);
    }

    public void addPlayer(List<Player> players,
                          PlayerType playerType,
                          ColorType colorType,
                          int totalButtonsPerPlayer) {
        if (playerType.equals(PlayerType.HUMAN)) {
            players.add(new HumanPlayer(colorType, totalButtonsPerPlayer));
        }
    }

    public Board getBoard(Game game) {
        return game.getBoard();
    }

    public void displayBoard(Game game) {
        getBoard(game).display();
    }

    // make move
    public void makeMove(Game game)  {
        game.move();
    }
}
