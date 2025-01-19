package TicTacToe.Models;

import TicTacToe.Exceptions.InvalidMoveException;
import TicTacToe.Strategy.WinningStrategy.WinningStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Game {

    private List<Player> players;
    private Player winner;
    private GameStatus gameStatus;
    private Board board;
    private int nextPlayerIndex;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;

    private Game(List<Player> players, int dimension, List<WinningStrategy> winningStrategy) {
        this.board = new Board(dimension);
        this.players = players;
        this.nextPlayerIndex = 0;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategy;

    }

    public static Builder builder() {
        return new Builder();
    }

    public void printBoard() {
        board.printBoard();
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        if (row < 0 || row >= board.getDimension() || col < 0 || col >= board.getDimension()) {
            return false;
        }

        //Whether the cell at which player is trying to make a move is empty or not.
        if (!board.getBoard().get(row).get(col).getStatus().equals(CellState.EMPTY)) {
            return false;
        }

        return true;
    }


    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerIndex);

        System.out.println("This is " + currentPlayer.getName() + "'s move.");

        //Player will choose the move that they want to make.
        Move move = currentPlayer.makeMove(board);

        //Game will validate if the move that player has chosen is valid or not.
        if (!validateMove(move)) {
            //throw some exception to the player.
            throw new InvalidMoveException("Invalid move, please retry");
        }

        //Move is valid, so apply this move to the board.
        int row = move.getCell().getRow(); // 2
        int col = move.getCell().getColumn(); // 1

        Cell cell = board.getBoard().get(row).get(col);
        cell.setStatus(CellState.OCCUPIED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();

        if (checkWinner(finalMove)) {
            winner = currentPlayer;
            gameStatus = GameStatus.FINISHED;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            gameStatus = GameStatus.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy : winningStrategies) {
            if (winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }



    @Getter
    public static class Builder {

        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        private Builder () {
            this.players = new ArrayList<>();
            this.dimension = 3;
            this.winningStrategies = new ArrayList<>();
        }

        private void validations() {
            validateBotCount();
            validateUniqueSymbols();
        }


        public Game build() {
            validations();
            return new Game(players, dimension, winningStrategies);
        }

        //Validations

        private void validateBotCount() {
            int count = 0;
            for (Player player : players) {
                if (player.getPlayerType().equals(PlayerType.BOT)) {
                    count += 1;
                    if (count > 1) {
                        throw new RuntimeException("Only one BOT is allowed per game");
                    }
                }
            }
        }

        private void validateUniqueSymbols() {
            Set<Character> symbolSet = new HashSet<>();
            for (Player player : players) {
                symbolSet.add(player.getSymbol().getAChar());
            }

            if (symbolSet.size() != dimension - 1) {
                throw new RuntimeException("Every player should have a unique symbol");
            }
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }
    }
}
