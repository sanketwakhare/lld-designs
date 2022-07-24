package com.sanket.designsnakeandladder.models;

import com.sanket.designsnakeandladder.models.players.Player;
import com.sanket.designsnakeandladder.strategies.HandleMoveStrategy;
import com.sanket.designsnakeandladder.strategies.UnlockButtonStrategy;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Game {

    private final Board board;
    private final List<Player> players;
    private final Dice dice;
    private final int buttonsPerPlayer;
    private GameStatus gameStatus;
    private final List<Player> rankings;
    private int lastPlayerMovedIndex;

    private HandleMoveStrategy handleMoveStrategy;

    private List<UnlockButtonStrategy> unlockButtonStrategies;

    private Game(Builder builder) {
        // immutable builder properties
        this.board = builder.board;
        this.players = builder.players;
        this.dice = builder.dice;
        this.buttonsPerPlayer = builder.buttonsPerPlayer;

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
        // get current player
        int lastPlayerMovedIndex = getLastPlayerMovedIndex();
        int currentPlayerIndex = lastPlayerMovedIndex + 1 % players.size();
        Player currentPlayer = players.get(currentPlayerIndex);

        // perform dice roll
        int dicedValue = dice.rollDice();

        // TODO
        for(UnlockButtonStrategy unlockButtonStrategy: unlockButtonStrategies) {
            unlockButtonStrategy.canUnlock(dicedValue);
        }

        // validate the move
        // make move - ask user to enter which button to move
        handleMoveStrategy.performMove(currentPlayer, dicedValue, this.board);

        // post-action: change position based on foreign entities
        setLastPlayerMovedIndex(currentPlayerIndex);
    }

    public void undo() {
        // TODO
    }

    public static class Builder {

        private Board board;
        private List<Player> players;
        private Dice dice;
        private int buttonsPerPlayer;

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

        public Game build() {
            // validations

            //create object
            return new Game(this);
        }
    }
}
