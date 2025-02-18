package TicTacToe.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Player {

    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner scanner = new Scanner(System.in);

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    Move makeMove(Board board) {
        //Take row, col in the input from the player.
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the row number where you want to make a move");
        int row = scanner.nextInt();

        System.out.println("Please enter the col number where you want to make a move");
        int col = scanner.nextInt();

        return new Move(this, new Cell(row, col));
    }

}
