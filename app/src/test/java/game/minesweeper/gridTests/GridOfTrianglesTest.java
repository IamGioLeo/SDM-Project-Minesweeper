package game.minesweeper.gridTests;

import game.minesweeper.grid.Coordinate;
import game.minesweeper.grid.GridOfTriangles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GridOfTrianglesTest implements Grid2DContract {

    @Override
    public Coordinate borderCoordinate() {
        return new Coordinate(5, 1);
    }

    @Override
    public int expectedNumberOfCornerNeighbors() {
        return 5;
    }

    @Override
    public int expectedNumberOfBorderNeighbors() {
        return 7;
    }

    @Override
    public int expectedNumberOfRows() {
        return 9;
    }

    @Override
    public int expectedNumberOfColumns() {
        return 9;
    }

    @Override
    public GridOfTriangles createGridWithSize(int numberOfRows, int numberOfColumns) {
        return new GridOfTriangles(numberOfRows, numberOfColumns);
    }

    @Override
    public GridOfTriangles createGrid() {
        return new GridOfTriangles(expectedNumberOfRows(), expectedNumberOfColumns());
    }

    @Override
    public Coordinate validCoordinate() {
        return new Coordinate(4, 2);
    }

    @Override
    public Coordinate invalidCoordinate() {
        return new Coordinate(11, 11);
    }

    @Override
    public Coordinate coordinateWithMaximumNumberOfNeighbors() {
        return new Coordinate(5, 5);
    }

    @Override
    public int expectedMaximumNumberOfNeighbors() {
        return 12;
    }

    @Test
    public void bottomLeftCornerHasCorrectNumberOfNeighbors() {
        GridOfTriangles grid = createGrid();
        Coordinate coordinate = new Coordinate(9, 1);

        int numberOfNeighbors = grid.getCellNeighbors(coordinate).size();

        assertEquals(4, numberOfNeighbors);
    }

    @Test
    public void topBorderCellsHaveCorrectNumberOfNeighbors() {
        GridOfTriangles grid = createGrid();
        Coordinate coordinate = new Coordinate(1, 4);
        Coordinate coordinate2 = new Coordinate(1, 5);

        int numberOfNeighbors = grid.getCellNeighbors(coordinate).size();
        int numberOfNeighbors2 = grid.getCellNeighbors(coordinate2).size();

        assertEquals(7, numberOfNeighbors);
        assertEquals(9, numberOfNeighbors2);
    }
}
