package TicTacToe;

import TicTacToe.Models.*;
import TicTacToe.Strategy.WinningStrategy.ColumnWinningStrategy;
import TicTacToe.Strategy.WinningStrategy.DiagonalWinningStrategy;
import TicTacToe.Strategy.WinningStrategy.RowWinningStrategy;
import TicTacToe.Strategy.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) {
        System.out.println("GAME STARTS");
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(
                new Player("Avinash Kumar", new Symbol('X'), PlayerType.HUMAN)
        );

        players.add(
                new Bot("Botium", new Symbol('O'), PlayerType.BOT, DifficultyLevel.EASY)
        );

        List<WinningStrategy> winningStrategies = List.of(
                new RowWinningStrategy(),
                new ColumnWinningStrategy(),
                new DiagonalWinningStrategy()
        );

        Game game = Game.builder().setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();

        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            //1. Show the board.
            //2. make a move.

            game.printBoard();
            game.makeMove();
        }

        game.printBoard();
        if (game.getGameStatus().equals(GameStatus.FINISHED)) {
            System.out.println(game.getWinner().getName() + " has won the game.");
        } else {
            System.out.println("GAME DRAW");
        }
    }
}

