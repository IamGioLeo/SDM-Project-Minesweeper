package game.minesweeper.grid;

public class InvalidGridSizeException extends RuntimeException {

    public InvalidGridSizeException(int rows, int columns) {
        super("Invalid grid size: " + rows + "x" + columns);
    }
}