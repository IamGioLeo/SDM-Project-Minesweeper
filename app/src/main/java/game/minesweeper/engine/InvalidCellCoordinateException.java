package game.minesweeper.engine;

public class InvalidCellCoordinateException extends RuntimeException {

    public InvalidCellCoordinateException(int row, int column) {
        super("Invalid cell coordinate: (" + row + ", " + column + ")");
    }
}
