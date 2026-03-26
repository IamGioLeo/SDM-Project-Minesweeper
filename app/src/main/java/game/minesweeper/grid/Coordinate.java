package game.minesweeper.grid;


public record Coordinate(int row, int column) implements CoordinateInterface {

    public boolean isFeasible() {
        return row >= 0 && column >= 0;
    }

    public String displayString() {
        return row + "," + column;
    }
}

