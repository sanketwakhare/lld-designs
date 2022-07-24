package com.sanket.designsnakeandladder;

import com.sanket.designsnakeandladder.controller.GameController;
import com.sanket.designsnakeandladder.models.Dice;
import com.sanket.designsnakeandladder.models.Game;
import com.sanket.designsnakeandladder.models.GameStatus;
import com.sanket.designsnakeandladder.models.foreignentities.ForeignEntity;
import com.sanket.designsnakeandladder.models.foreignentities.Ladder;
import com.sanket.designsnakeandladder.models.foreignentities.Snake;
import com.sanket.designsnakeandladder.models.players.ColorType;
import com.sanket.designsnakeandladder.models.players.HumanPlayer;
import com.sanket.designsnakeandladder.models.players.Player;
import com.sanket.designsnakeandladder.models.players.PlayerType;
import com.sanket.designsnakeandladder.strategies.*;

import java.util.ArrayList;
import java.util.List;

public class GameSimulator {

    public static void main(String[] args) {

        // board configuration
        int boardDimension = 100;

        // player and button configuration
        int totalPlayers = 2;
        int totalButtonsPerPlayer = 2;
        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(ColorType.BLUE, totalButtonsPerPlayer));
        players.add(new HumanPlayer(ColorType.RED, totalButtonsPerPlayer));

        // dice configuration
        int minDiceNumber = 1;
        int maxDiceNumber = 6;
        Dice dice = new Dice(minDiceNumber, maxDiceNumber);

        // strategies
        HandleMoveStrategy handleMoveStrategy = new NormalMoveStrategy();

        List<UnlockButtonStrategy> unlockButtonStrategies = new ArrayList<>();
        unlockButtonStrategies.add(new OneDiceRollUnlockButtonStrategy());
        unlockButtonStrategies.add(new SixDiceRollUnlockButtonStrategy());

        List<ForeignEntity> foreignEntities = new ArrayList<>();
        foreignEntities.add(new Snake(97, 41));
        foreignEntities.add(new Snake(88, 3));
        foreignEntities.add(new Snake(79, 45));
        foreignEntities.add(new Ladder(5, 42));
        foreignEntities.add(new Ladder(38, 52));
        foreignEntities.add(new Ladder(65, 85));

        GameController gameController = new GameController();
        Game game = gameController.createGame(
                boardDimension,
                foreignEntities,
                players,
                totalButtonsPerPlayer,
                dice,
                handleMoveStrategy,
                unlockButtonStrategies
        );

        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            // TODO
        }
    }
}