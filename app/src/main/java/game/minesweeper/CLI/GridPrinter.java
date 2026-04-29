package game.minesweeper.CLI;

import game.minesweeper.grid.Cell;
import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfSquares;
import game.minesweeper.engine.CellView;
import game.minesweeper.engine.GameController;

public class GridPrinter {

    public static void print(GameController controller, GridOfSquares grid) {

        System.out.print("  ");
        for (int i = 1; i <= grid.getNumberOfColumns(); i++) {
            System.out.printf("%3d", i);
        }

        System.out.println();

        for (int row = 1; row <= grid.getNumberOfRows(); row++) {
            System.out.printf("%2d", row);
            for (int col = 1; col <= grid.getNumberOfColumns(); col++) {

                CellView cell = controller.getCellView(row, col);

                String display;

                if (!cell.revealed()) {
                    display = cell.flagged() ? "F" : "\u2580";
                } else if (cell.mine()) {
                    display = "*";
                } else {
                    display = String.valueOf(cell.neighborMineCount());
                }

                System.out.printf("%3s", display);
            }

            System.out.println();
        }
    }
}