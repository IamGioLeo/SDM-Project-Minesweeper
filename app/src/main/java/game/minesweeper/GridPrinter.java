package game.minesweeper;

public class GridPrinter {

    public static void print(Grid grid) {

        for (int row = 1; row <= grid.getNumberOfRows(); row++) {
            for (int col = 1; col <= grid.getNumberOfColumns(); col++) {

                Cell cell = grid.getCell(new Coordinate(row, col));

                if (!cell.isRevealed()) {

                    if (cell.isFlagged()) {
                        System.out.print("F ");
                    } else {
                        System.out.print(". ");
                    }

                } else if (cell.hasMine()) {

                    System.out.print("* ");

                } else {

                    System.out.print(cell.neighborsMineCount() + " ");
                }
            }

            System.out.println();
        }
    }
}