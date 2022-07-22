package com.sanket.designtictactoe.factories;

import com.sanket.designtictactoe.models.DifficultyLevel;
import com.sanket.designtictactoe.strategies.botplaying.BotPlayingStrategy;
import com.sanket.designtictactoe.strategies.botplaying.EasyDifficultyBotPlayingStrategy;
import com.sanket.designtictactoe.strategies.botplaying.HardDifficultyBotPlayingStrategy;
import com.sanket.designtictactoe.strategies.botplaying.MediumDifficultyBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy createBotPlayingStrategy(DifficultyLevel difficultyLevel) {
        return switch (difficultyLevel) {
            case EASY -> new EasyDifficultyBotPlayingStrategy();
            case MEDIUM -> new MediumDifficultyBotPlayingStrategy();
            case HARD -> new HardDifficultyBotPlayingStrategy();
        };
    }
}
