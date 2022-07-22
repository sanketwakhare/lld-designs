package com.sanket.designtictactoe.models;

import com.sanket.designtictactoe.factories.BotPlayingStrategyFactory;
import com.sanket.designtictactoe.strategies.botplaying.BotPlayingStrategy;
import lombok.Getter;

@Getter
public class Bot extends Player {

    private DifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Symbol symbol, DifficultyLevel difficultyLevel) {
        super(PlayerType.BOT, symbol);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory
                .createBotPlayingStrategy(this.difficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        return this.botPlayingStrategy.makeNextMove(board, this);
    }
}
