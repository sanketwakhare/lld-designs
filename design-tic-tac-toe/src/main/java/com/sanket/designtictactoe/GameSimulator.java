package com.sanket.designtictactoe;

import com.sanket.designtictactoe.controller.GameController;
import com.sanket.designtictactoe.models.*;
import com.sanket.designtictactoe.strategies.botplaying.EasyDifficultyBotPlayingStrategy;
import com.sanket.designtictactoe.strategies.botplaying.BotPlayingStrategy;
import com.sanket.designtictactoe.strategies.winning.ColumnWinningStrategy;
import com.sanket.designtictactoe.strategies.winning.DiagonalWinningStrategy;
import com.sanket.designtictactoe.strategies.winning.RowWinningStrategy;
import com.sanket.designtictactoe.strategies.winning.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class GameSimulator {

    public static void main(String[] args) {

        int dimension = 3;
        List<Player> players = new ArrayList<>();
        BotPlayingStrategy makeMoveStrategy = new EasyDifficultyBotPlayingStrategy();
        Player p1 = new HumanPlayer(new Symbol('x'));
        Player p2 = new Bot(new Symbol('o'), DifficultyLevel.EASY);
        players.add(p1);
        players.add(p2);

        WinningStrategy rowWinningStrategy = new RowWinningStrategy();
        WinningStrategy columnWinningStrategy = new ColumnWinningStrategy();
        WinningStrategy diagonalWinningStrategy = new DiagonalWinningStrategy();
        List<WinningStrategy> winningStrategies = List.of(
                rowWinningStrategy, columnWinningStrategy, diagonalWinningStrategy);

        GameController controller = new GameController();
        Game game = controller.createGame(dimension, players, winningStrategies);
        while (GameStatus.IN_PROGRESS.equals(game.getGameStatus())) {
            controller.makeMove(game);
            controller.displayBoard(game);
        }

        if (GameStatus.DRAW.equals(game.getGameStatus())) {
            System.out.println("Game Drawn");
        }

        if (GameStatus.FINISHED.equals(game.getGameStatus())) {
            System.out.println("Winner is " + game.getWinner().getSymbol());
        }
    }
}
