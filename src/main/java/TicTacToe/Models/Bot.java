package TicTacToe.Models;

import TicTacToe.Factorie.BotPlayingStrategyFactory;
import TicTacToe.Strategy.BotPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player {

    private DifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, PlayerType playerType, DifficultyLevel difficultyLevel) {
        super(name, symbol, playerType);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategyFactory(difficultyLevel);
    }

    public Move makeMove(Board board) {

       return botPlayingStrategy.makeMove(board, this);
    }
}
