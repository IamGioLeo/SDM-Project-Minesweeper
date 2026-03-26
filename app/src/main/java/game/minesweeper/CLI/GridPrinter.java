package game.minesweeper.CLI;

import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;

public class GridPrinter {

    public static void print(GridOfSquares grid) {

        System.out.print("  ");
        for (int i = 1; i <= grid.getNumberOfColumns(); i++) {
            System.out.printf("%3d", i);
        }

        System.out.println();

        for (int row = 1; row <= grid.getNumberOfRows(); row++) {
            System.out.printf("%2d", row);
            for (int col = 1; col <= grid.getNumberOfColumns(); col++) {

                Cell cell = grid.getCell(new Coordinate(row, col));

                String display;

                if (!cell.isRevealed()) {
                    display = cell.isFlagged() ? "F" : "\u2580";
                } else if (cell.hasMine()) {
                    display = "*";
                } else {
                    display = String.valueOf(cell.neighborsMineCount());
                }

                System.out.printf("%3s", display);
            }

            System.out.println();
        }
    }
}