package TicTacToe.Strategy.BotPlayingStrategy;

import TicTacToe.Models.Board;
import TicTacToe.Models.Move;
import TicTacToe.Models.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player);
}
