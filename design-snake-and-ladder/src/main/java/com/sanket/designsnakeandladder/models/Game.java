package com.sanket.designsnakeandladder.models;

import com.sanket.designsnakeandladder.models.players.Button;
import com.sanket.designsnakeandladder.models.players.ButtonStatus;
import com.sanket.designsnakeandladder.models.players.Player;
import com.sanket.designsnakeandladder.strategies.HandleMoveStrategy;
import com.sanket.designsnakeandladder.strategies.UnlockButtonStrategy;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

@Getter
public class Game {

    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final int buttonsPerPlayer;
    private GameStatus gameStatus;
    private final List<Player> rankings;
    private int lastPlayerMovedIndex;

    private final HandleMoveStrategy handleMoveStrategy;

    private final List<UnlockButtonStrategy> unlockButtonStrategies;

    private Game(Builder builder) {
        // immutable builder properties
        this.board = builder.board;
        this.players = builder.players;
        this.dice = builder.dice;
        this.buttonsPerPlayer = builder.buttonsPerPlayer;
        this.handleMoveStrategy = builder.handleMoveStrategy;
        this.unlockButtonStrategies = builder.unlockButtonStrategies;

        // initialize mutable properties
        this.gameStatus = GameStatus.NOT_STARTED;
        this.rankings = new LinkedList<>();
        this.lastPlayerMovedIndex = -1;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void addPlayerRanking(Player player) {
        this.rankings.add(player);
    }

    public void setLastPlayerMovedIndex(int lastPlayerMovedIndex) {
        this.lastPlayerMovedIndex = lastPlayerMovedIndex;
    }

    public void move() {
        Scanner scanner = new Scanner(System.in);
        // get current player
        int lastPlayerMovedIndex = getLastPlayerMovedIndex();
        int currentPlayerIndex = (lastPlayerMovedIndex + 1) % players.size();
        Player currentPlayer = players.get(currentPlayerIndex);

        // perform dice roll
        int dicedValue = dice.rollDice();
        System.out.println("Player " + currentPlayer.getColor() + " turn: Dice value " + dicedValue);

        // check if any button can be unlocked
        boolean canUnlock = false;
        for (UnlockButtonStrategy unlockButtonStrategy : unlockButtonStrategies) {
            canUnlock = unlockButtonStrategy.canUnlock(dicedValue);
            if (canUnlock) break;
        }

        if (canUnlock) {
            List<Button> lockedButtons = currentPlayer.getButtons(ButtonStatus.LOCKED);
            if (lockedButtons.size() > 0) {
                System.out.print("Want to unlock buttons? (y/n) ");
                String shouldUnlock = scanner.nextLine();
                if (shouldUnlock.equalsIgnoreCase("y")) {
                    // unlock one of the button
                    Button button = lockedButtons.get(0);
                    button.setButtonStatus(ButtonStatus.IN_PROGRESS);
                    button.setPosition(1);
                    //roll the dice again for same player
                    return;
                }
            }
        }

        // validate the move
        if (handleMoveStrategy.isValidMove(currentPlayer, dicedValue, this.board)) {
            // make move - ask user to enter which button to move
            try {
                handleMoveStrategy.performMove(currentPlayer, dicedValue, this.board);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
        // post-action: change position based on foreign entities
        setLastPlayerMovedIndex(currentPlayerIndex);

        if (currentPlayer.getButtons(ButtonStatus.FINISHED).size() == buttonsPerPlayer) {
            rankings.add(currentPlayer);
            players.remove(currentPlayer);
            if (players.size() == 1) setGameStatus(GameStatus.FINISHED);
        }
    }

    public void undo() {
        // TODO
    }

    public static class Builder {

        private Board board;
        private List<Player> players;
        private Dice dice;
        private int buttonsPerPlayer;

        private HandleMoveStrategy handleMoveStrategy;

        private List<UnlockButtonStrategy> unlockButtonStrategies;

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public Builder setDice(Dice dice) {
            this.dice = dice;
            return this;
        }

        public Builder setButtonsPerPlayer(int buttonsPerPlayer) {
            this.buttonsPerPlayer = buttonsPerPlayer;
            return this;
        }

        public Builder setHandleMoveStrategy(HandleMoveStrategy handleMoveStrategy) {
            this.handleMoveStrategy = handleMoveStrategy;
            return this;
        }

        public Builder setUnlockButtonStrategies(List<UnlockButtonStrategy> unlockButtonStrategies) {
            this.unlockButtonStrategies = unlockButtonStrategies;
            return this;
        }

        public Game build() {
            // validations

            //create object
            return new Game(this);
        }
    }
}
