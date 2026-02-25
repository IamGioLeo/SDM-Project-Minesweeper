package game.minesweeper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {

    private final int numberOfRows;
    private final int numberOfColumns;
    private final Map<Coordinate, Cell> cells;


    public Grid(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.cells = new HashMap<>();
        initCells();
    }

    private void initCells() {
        for (int row = 1; row <= numberOfRows; row++) {
            for (int column = 1; column <= numberOfColumns; column++) {
                Coordinate coordinate = new Coordinate(row, column);
                cells.put(coordinate, new Cell());
            }
        }
    }

    public int getNumberOfColumns() { return numberOfColumns; }

    public int getNumberOfRows() { return numberOfRows; }

    public Cell getCell(int row, int column) {
        Coordinate coordinate = new Coordinate(row, column);
        return cells.get(coordinate);
    }

    public Cell getCell(Coordinate coordinate) { return cells.get(coordinate); }

    public List<Cell> getCellNeighbors(int row, int column) {
        List<Cell> neighbors = new ArrayList<>();

        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                Coordinate neighbor = new Coordinate(row + dr, column + dc);
                Cell cell = cells.get(neighbor);
                if (cell != null) neighbors.add(cell);
            }
        }
        return neighbors;
    }

    public List<Cell> getCellNeighbors(Coordinate coordinate) {

        int row = coordinate.row();
        int column = coordinate.column();

        return getCellNeighbors(row, column);
    }

    public List<Cell> getAllCells() { return new ArrayList<>(cells.values()); }
}
