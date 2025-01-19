package TicTacToe.Strategy.BotPlayingStrategy;

import TicTacToe.Models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        //write a logic to fill to next empty cell

        for(int i = 0; i < board.getDimension(); i++) {
            for(int j = 0; j < board.getDimension(); j++) {
                if(board.getBoard().get(i).get(j).getStatus() == CellState.EMPTY) {
                    return new Move(player, new Cell(i, j));
                }
            }
        }
        return null;
    }
}
