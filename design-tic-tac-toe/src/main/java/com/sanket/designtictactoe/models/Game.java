package com.sanket.designtictactoe.models;

import com.sanket.designtictactoe.exceptions.InvalidUndoOperationException;
import com.sanket.designtictactoe.strategies.winning.WinningStrategy;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
public class Game {
    private final Board board;
    private final List<Player> players;
    private final List<WinningStrategy> winningStrategies;
    private int lastPlayerMovedIndex;
    private final List<Move> movesPerformed;
    private GameStatus gameStatus;

    private Player winner;

    private Game(Builder builder) {
        this.board = builder.board;
        this.players = builder.players;
        this.winningStrategies = builder.winningStrategies;
        this.lastPlayerMovedIndex = builder.lastPlayerMovedIndex;
        this.movesPerformed = builder.movesPerformed;
        this.gameStatus = builder.gameStatus;
    }

    public void setLastPlayerMovedIndex(int lastPlayerMovedIndex) {
        this.lastPlayerMovedIndex = lastPlayerMovedIndex;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public static class Builder {
        private Board board;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int lastPlayerMovedIndex;
        private List<Move> movesPerformed;
        private GameStatus gameStatus;

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addPlayer(Player players) {
            this.players.add(players);
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setLastPlayerMovedIndex(int lastPlayerMovedIndex) {
            this.lastPlayerMovedIndex = lastPlayerMovedIndex;
            return this;
        }

        public Builder setMovesPerformed(List<Move> movesPerformed) {
            this.movesPerformed = movesPerformed;
            return this;
        }

        public Builder setGameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }

        public Game build() {
            return new Game(this);
        }
    }

    public void makeMove() throws InvalidUndoOperationException {
        // get current player
        lastPlayerMovedIndex++;
        Player player = this.players.get(lastPlayerMovedIndex % this.players.size());

        // perform move
        Move move = player.makeMove(board);
        if (Objects.isNull(move)) {
            this.undo();
        } else {
            movesPerformed.add(move);

            // check if won
            for (WinningStrategy strategy : winningStrategies) {
                if (strategy.checkIfWon(board, move.getCell(), player)) {
                    setWinner(move.getPlayer());
                    setGameStatus(GameStatus.FINISHED);
                    break;
                }
            }

            if (movesPerformed.size() == board.getDimension() * board.getDimension()) {
                setGameStatus(GameStatus.DRAW);

            }
        }
    }

    public void undo() throws InvalidUndoOperationException {
        if (this.movesPerformed.size() > 1) {
            Move move = this.movesPerformed.get(this.movesPerformed.size() - 1);
            Cell cell = move.getCell();
            board.getCell(cell.getRow(), cell.getColumn()).setSymbol(null);
            this.movesPerformed.remove(this.movesPerformed.size() - 1);
            move = this.movesPerformed.get(this.movesPerformed.size() - 1);
            cell = move.getCell();
            board.getCell(cell.getRow(), cell.getColumn()).setSymbol(null);
            this.movesPerformed.remove(this.movesPerformed.size() - 1);
        } else {
            throw new InvalidUndoOperationException();
        }
    }
}
