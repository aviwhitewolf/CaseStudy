package TicTacToe.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;

@Getter
@Setter
public class Move {
    private Cell cell;
    private Player player;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

}
