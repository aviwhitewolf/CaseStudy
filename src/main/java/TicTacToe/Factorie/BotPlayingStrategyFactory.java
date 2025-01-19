package TicTacToe.Factorie;

import TicTacToe.Models.DifficultyLevel;
import TicTacToe.Strategy.BotPlayingStrategy.BotPlayingStrategy;
import TicTacToe.Strategy.BotPlayingStrategy.EasyBotPlayingStrategy;
import TicTacToe.Strategy.BotPlayingStrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyFactory(DifficultyLevel botDifficultyLevel) {
        if (botDifficultyLevel.equals(DifficultyLevel.EASY)) {
            return new EasyBotPlayingStrategy();
        } else if (botDifficultyLevel.equals(DifficultyLevel.MEDIUM)) {
            return new MediumBotPlayingStrategy();
        } else {
            return null;
        }
    }
}
