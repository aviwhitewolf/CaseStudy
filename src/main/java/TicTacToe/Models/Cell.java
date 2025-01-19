package TicTacToe.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cell {
    private int row;
    private int column;
    private Player player;
    private CellState status;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.status = CellState.EMPTY;
    }

    public void display() {
        if (player == null) {
            //cell is empty
            System.out.print(" _ ");
        } else {
            System.out.print(" " + player.getSymbol().getAChar() + " ") ;
        }
    }
}
